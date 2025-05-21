## 📌 Resilience4j Nedir?

[🔗 Medium Makalesi – Çok iyi anlatıyor](https://umitsamimi.medium.com/circuit-breaker-resilience4j-7e1082610c52)

* Bilindiği üzere, arka plan (back-end) servislerinin giderek karmaşıklaşması ve tek parça hâlinde sürdürülebilirliğinin zorlaşması sonucunda, mikroservis mimarisi kullanılarak arka plan servislerinin birbirleriyle iletişim halinde olan, nispeten daha küçük servisler hâlinde düzenlenmesi oldukça popüler hale gelmiştir.
* Bu servisler, çoğunlukla HTTP protokolünü kullanarak, kapalı bir ağ üzerinden birbirleriyle haberleşirler.
* Ancak, bu yapı bazı ek problemleri de beraberinde getirebilir.
* Örneğin, projemde `user-service` servisi, kendisine gelen istekleri karşılamak üzere `department-service` ile iletişime geçiyor olsun.
* `department-service` üzerinde meydana gelebilecek sistem hataları, yeni bir sürümün sunucuya yüklenmesi ya da bu yeni sürümde çıkabilecek kararlılık sorunları gibi nedenlerle bu servise yapılan istekler zamanında yanıtlanamayabilir ve hata dönebilir.
* Bu durumda, `department-service` servisinden dönen hata, `user-service` servisine de yansıyacaktır.
* Hata, `department-service`’e çağrı yapılan katmandan itibaren üst katmanlara (service, controller vb.) fırlatılacak ve `user-service`'e istek yapan istemci uygun bir yanıt alamayacaktır.
* Bu şekilde oluşan bir hata zinciri, son kullanıcının uygulamayı düzgün şekilde kullanamamasına sebep olur.
* Bu gibi durumlar çeşitli yöntemlerle önlenebilir.

### 🔁 Retry (Yeniden Deneme)

* Beklenmedik bir yanıtın ya da hiç yanıt alınamamasının, isteği tekrar göndererek düzeltilebileceğini varsaydığımız durumlarda kullanılır.
* Yeniden deneme işlemi, başarısız olan isteklerin belirli sayıda tekrar gönderilmesini sağlar.
* Aşağıdaki durumlarda faydalıdır:

    * Geçici ağ sorunları (örneğin paket kaybı)
    * Veritabanı kesintisi gibi hedef hizmetin iç hataları
    * Aşırı yüklenme nedeniyle hizmetin yavaşlaması ya da hiç yanıt verememesi

### 🛑 Fallback (Geri Dönüş)

* Başka bir hizmete yapılan istek başarısız olduğunda, sistemin çalışmaya devam etmesini sağlar.
* Hesaplamayı iptal etmek yerine, önceden tanımlanmış bir geri dönüş (default) değeri döndürülür.

### ⏱️ Timeout (Zaman Aşımı)

* Belirli bir süre içinde yanıt alınamayan isteklerin başarısız sayılmasıdır.
* Amaç, yanıtlar için süresiz beklemekten kaçınmaktır.
* Çoğu HTTP istemcisi varsayılan bir zaman aşımı süresi ile gelir.

### ⚡ Circuit Breaker (Devre Kesici)

* Circuit Breaker deseni, adından da anlaşılacağı üzere elektronik devrelerdeki sigorta (devre kesici) mekanizmasına benzer şekilde çalışır.
* Servisler arası iletişimi izleyerek, hata oranı belirli bir eşiği geçtiğinde iletişimi keser.
* Örneğin, bir API sürekli `500 Internal Server Error` dönüyorsa, devre kesici bu servise giden trafiği geçici olarak durdurur.
* Devre kesici üç modda çalışır:

    * **Closed (Kapalı)**: Tüm istekler geçer, sistem normal çalışır. Ancak hata oranı belirli bir seviyeyi geçerse devre **Open** moduna geçer.
    * **Open (Açık)**: Tüm istekler engellenir. Sistem, hatanın devam ettiğini varsayar ve kısa süreli dinlenmeye geçer. Bu sırada fallback (geri dönüş) değerleri döndürülür.
    * **Half-Open (Yarı Açık)**: Belirli bir süre sonra birkaç isteğe izin verilir. Eğer bu istekler başarılı olursa sistem tekrar **Closed** moda döner; aksi halde tekrar **Open** moda geçer.

---

## 📘 `application.yml` Açıklamaları

```yaml
spring.application.name=library-service
```

Bu satır, uygulamanın adını belirtir. Burada uygulamanın adı `"library-service"` olarak ayarlanmıştır.

```yaml
eureka.instance.instance-id=${spring.application.name}:${random.value}
```

Bu satır, Eureka sunucusunda mikroservisin benzersiz kimliğini tanımlar. Uygulama adı ve rastgele bir değer ile birleşik bir kimlik oluşturur.

```yaml
eureka.instance.prefer-ip-address=true
```

Bu ayar, mikroservisin Eureka’ya IP adresi ile kaydolmasını sağlar. Hostname yerine IP adresi tercih edilir.

```yaml
eureka.client.service-url.defaultZone=${EUREKA_URI:http://localhost:8761/eureka}
```

Bu satır, Eureka sunucusunun adresini belirtir. `EUREKA_URI` ortam değişkeni tanımlıysa onu kullanır; tanımlı değilse varsayılan olarak `http://localhost:8761/eureka` adresini kullanır.
