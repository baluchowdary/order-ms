==> springboot 3.x

==> Kafka version - 2.8.2

==> java 17

==> Oracle11g

==> Maven3.8

==> update server.peroperties & zookeeper.properties files log paths with kafka directory path
==>To start Zookeeper:
C:\Users\bhard\kollu\sw\kafka_2.13>bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

==>To start Kafka:
C:\Users\bhard\kollu\sw\kafka_2.13>bin\windows\kafka-server-start.bat .\config\server.properties

==> Create topic::
kafka-topics.bat --create --topic new-order --bootstrap-server localhost:9092
kafka-topics.bat --create --topic new-payments --bootstrap-server localhost:9092
kafka-topics.bat --create --topic reversed-orders --bootstrap-server localhost:9092
kafka-topics.bat --create --topic new-stock --bootstrap-server localhost:9092

==> See Topic details::
kafka-topics.bat --describe --topic new-order --bootstrap-server localhost:9092
kafka-topics.bat --describe --topic new-payments --bootstrap-server localhost:9092
kafka-topics.bat --describe --topic reversed-orders --bootstrap-server localhost:9092
kafka-topics.bat --describe --topic new-stock --bootstrap-server localhost:9092

==>See Consumer group details::
kafka-consumer-groups.bat --list --bootstrap-server localhost:9092

==>Read topic details::
kafka-console-consumer.bat --topic new-order --from-beginning --bootstrap-server localhost:9092
kafka-console-consumer.bat --topic new-payments --from-beginning --bootstrap-server localhost:9092
kafka-console-consumer.bat --topic reversed-orders --from-beginning --bootstrap-server localhost:9092
kafka-console-consumer.bat --topic new-stock --from-beginning --bootstrap-server localhost:9092

=================
DB:
8081
jdbc:h2:mem:ordersdb
select * from order_table;

8082
jdbc:h2:mem:paymentdb
select * from payment_table;

8083
jdbc:h2:mem:stockdb
select * from ware_house_table;

8084
jdbc:h2:mem:deliverydb
select * from delivery_table;

Testing:
http://localhost:8081/api/orders

{
    "item": "test",
    "quantity": 10,
    "amount": 100,
    "address": "Michigan",
    "paymentMode" : "Credit card"
}

http://localhost:8083/api/addItems

{
    "item": "test",
    "quantity": 100
}

![image](https://github.com/baluchowdary/order-ms/assets/12624930/bd28fb7c-c3fd-46d9-866a-166164f92a34)

![image](https://github.com/baluchowdary/order-ms/assets/12624930/d9767462-26c4-4857-9bc3-9317e082a743)

![image](https://github.com/baluchowdary/order-ms/assets/12624930/c5db2830-53c9-4701-98fe-19e898ffdea6)

![image](https://github.com/baluchowdary/order-ms/assets/12624930/e26d4613-9ce8-489b-a6f4-a1cf4b3d22f9)

=====> Branch's

==> master -> Initial project setup and Integrated with Kafka

==> phase_1 -> Integrated with Oracle11g

==> phase_2 -> Integrated with Angular16

==> phase_3 -> Angular CRUD

==> phase_4 -> Added add Items functionality

