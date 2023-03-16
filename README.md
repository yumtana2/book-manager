# 書籍管理アプリケーション

### 環境

* Spring Boot
* Gradle
* JOOQ
* Flyway Migration
* Docker(事前にインストールが必要)
* macOS 13.2.1
* IntelliJ IDEA

### 構築
    
build+実行
```shell
$./gradlew bootRun  
```
DB更新した場合
```shell
$ ./gradlew flywayMigrate
$ ./gradlew generateJooq
```
### 動作確認

#### sample
```shell
$ curl "http://localhost:8080/book/list?authorId=2"                                                       
{"bookList":[{"bookId":2,"authorId":2,"title":"お金2.0","author":"山本秀五郎"},{"bookId":3,"authorId":2,"title":"ヒトはなぜ考え、感じ、行動するのか","author":"山本秀五郎"}]}%  
```

* 書籍情報の取得
```shell
$ curl "http://localhost:8080/book/list?authorId={ID}"                                                       
```
* 更新
```shell
$ curl -H 'Content-Type:application/json' -X PUT -d '{"id":{ID},"title":"{SpringBoot入門111}"}' "http://localhost:8080/book/update"
```
* 削除
```shell
$ curl -X DELETE "http://localhost:8080/admin/book/delete/{ID}"                                                      
```