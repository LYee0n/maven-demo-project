

https://www.rabbitmq.com/management-cli.html

```sh

openssl req -x509 -newkey rsa:4096 -sha256 -days 3650 -nodes -keyout rabbitmq.key -out rabbitmq.crt
# /etc/rabbitmq/rabbitmq.conf
ssl_options.cacertfile = /etc/rabbitmq/ssl/rabbitmq.crt
ssl_options.certfile   = /etc/rabbitmq/ssl/rabbitmq.crt
ssl_options.keyfile    = /etc/rabbitmq/ssl/rabbitmq.key
ssl_options.verify     = verify_peer
ssl_options.fail_if_no_peer_cert = true


rabbitmqadmin declare exchange name=my-new-exchange type=fanout -u admin -p admin
rabbitmqadmin declare queue name=my-new-queue durable=false -u admin -p admin
rabbitmqadmin declare binding source=my-new-exchange destination_type="queue" destination=my-new-queue routing_key=test -u admin -p admin

rabbitmqadmin list queues vhost name node messages -u admin -p admin

rabbitmqadmin publish exchange=my-new-exchange routing_key=test payload="hello, world" -u admin -p admin
rabbitmqadmin get queue=my-new-queue ackmode=ack_requeue_false -u admin -p admin
```