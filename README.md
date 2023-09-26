# SberCoffee Backend Service

UPD: JavaDoc доступен по [ссылке](https://github.com/DmitriiDerendyaev/SberCoffee/blob/main/documentation/index.html)
UPDD: В ветке refactor можно увидеть улучшение и рефакторинг кода

Добро пожаловать в бэкенд-сервис онлайн-кофейни SberCoffee от Сбербанка! Этот сервис предоставляет API для управления клиентами, сотрудниками, заказами кофе, товарами и статусами заказов. Вы можете использовать этот сервис для управления вашей кофейней и предоставления онлайн-заказов клиентам.

## Доступные запросы

После запуска проекта документация доступна по [ссылке](http://localhost:8080/swagger-ui/index.html) с использованием Swagger

### Client Controller

| Запрос                        | Описание                                   |
| ------------------------------ | ------------------------------------------ |
| GET /clients                   | Получить список всех клиентов.             |
| GET /clients/{id}             | Получить информацию о клиенте по ID.      |
| POST /clients                 | Создать нового клиента.                   |
| PUT /clients/{id}             | Обновить информацию о клиенте по ID.      |
| DELETE /clients/{id}          | Удалить клиента по ID.                    |

### Position Controller

| Запрос                        | Описание                                   |
| ------------------------------ | ------------------------------------------ |
| GET /positions                 | Получить список всех доступных должностей сотрудников. |
| GET /positions/{id}           | Получить информацию о должности сотрудника по ID.      |
| POST /positions               | Создать новую должность сотрудника.                   |
| PUT /positions/{id}           | Обновить информацию о должности сотрудника по ID.      |
| DELETE /positions/{id}        | Удалить должность сотрудника по ID.                    |

### Coffee Order Controller

| Запрос                        | Описание                                   |
| ------------------------------ | ------------------------------------------ |
| GET /coffee-orders             | Получить список всех заказов кофе.         |
| GET /coffee-orders/{id}       | Получить информацию о заказе кофе по ID.  |
| POST /coffee-orders           | Создать новый заказ кофе.                 |
| PUT /coffee-orders/{id}       | Обновить информацию о заказе кофе по ID.  |
| DELETE /coffee-orders/{id}    | Удалить заказ кофе по ID.                  |

### Item Controller

| Запрос                        | Описание                                   |
| ------------------------------ | ------------------------------------------ |
| GET /items                     | Получить список всех доступных товаров (виды кофе и их цены). |
| GET /items/{id}               | Получить информацию о товаре по ID.       |
| POST /items                   | Добавить новый товар (вид кофе) в ассортимент.              |
| PUT /items/{id}               | Обновить информацию о товаре по ID.       |
| DELETE /items/{id}            | Удалить товар из ассортимента по ID.       |

### Status Controller

| Запрос                        | Описание                                   |
| ------------------------------ | ------------------------------------------ |
| GET /statuses                 | Получить список всех доступных статусов заказов. |
| GET /statuses/{id}           | Получить информацию о статусе заказа по ID.      |
| POST /statuses               | Создать новый статус заказа.                   |
| PUT /statuses/{id}           | Обновить информацию о статусе заказа по ID.      |
| DELETE /statuses/{id}        | Удалить статус заказа по ID.                    |

### Staff Controller

| Запрос                        | Описание                                   |
| ------------------------------ | ------------------------------------------ |
| GET /staff                    | Получить список всех сотрудников.           |
| GET /staff/{id}               | Получить информацию о сотруднике по ID.    |
| POST /staff                   | Создать нового сотрудника.                 |
| PUT /staff/{id}               | Обновить информацию о сотруднике по ID.    |
| DELETE /staff/{id}            | Удалить сотрудника по ID.                  |

## Примеры представления таблиц в JSON

### Клиенты (Client)

```json
[
  {
    "id": 3,
    "name": "Иван",
    "surname": "Иванов",
    "patronymic": "Иванович",
    "phoneNumber": "81234567890",
    "address": "ул. Примерная, 123",
    "email": "ivan@example.com",
    "birthday": "1990-05-15"
  },
  {
    "id": 4,
    "name": "Мария",
    "surname": "Петрова",
    "patronymic": "Сергеевна",
    "phoneNumber": "89876543210",
    "address": "ул. Тестовая, 456",
    "email": "maria@example.com",
    "birthday": "1985-08-20"
  },
  {
    "id": 5,
    "name": "Алексей",
    "surname": "Сидоров",
    "patronymic": "Алексеевич",
    "phoneNumber": "85555555555",
    "address": "ул. Примерный проезд, 789",
    "email": "alex@example.com",
    "birthday": "1978-03-10"
  }
]
```

### Заказы кофе (CoffeeOrder)
```json
[
  {
    "id": 11,
    "client": {
      "id": 3,
      "name": "Иван",
      "surname": "Иванов",
      "patronymic": "Иванович",
      "phoneNumber": "81234567890",
      "address": "ул. Примерная, 123",
      "email": "ivan@example.com",
      "birthday": "1990-05-15"
    },
    "item": {
      "id": 3,
      "name": "Капучино",
      "price": 150.0
    },
    "quantity": 2,
    "status": {
      "id": 5,
      "name": "Создан"
    },
    "staff": {
      "id": 18,
      "name": "Дерендяев",
      "surname": "Дмитрий",
      "patronymic": "Сергеевич",
      "position": "Владелец",
      "phoneNumber": "89225070232",
      "address": "г. Москва, ул. Пушкина, д. 10"
    },
    "startTime": "2023-09-22T10:52:21.072094",
    "total": 300.00
  }
]
```

### Товары (Item)

```json
[
  {
    "id": 3,
    "name": "Капучино",
    "price": 150.0
  },
  {
    "id": 4,
    "name": "Латте",
    "price": 130.0
  },
  {
    "id": 5,
    "name": "Эспрессо",
    "price": 100.0
  }
]
```

### Должности сотрудников (Position)
```json
[
  {
    "id": 4,
    "name": "Бариста",
    "description": "Приготавливает кофе и обслуживает клиентов"
  },
  {
    "id": 5,
    "name": "Менеджер",
    "description": "Руководит точкой"
  },
  {
    "id": 6,
    "name": "Владелец",
    "description": "Владелец сети"
  }
]
```

### Статусы заказов (Status)

```json
[
  {
    "id": 5,
    "name": "Создан"
  },
  {
    "id": 6,
    "name": "В процессе"
  },
  {
    "id": 7,
    "name": "Завершен"
  }
]
```

### Сотрудники (Staff)
```json
[
    {
        "id": 18,
        "name": "Дерендяев",
        "surname": "Дмитрий",
        "patronymic": "Сергеевич",
        "position": "Владелец",
        "phoneNumber": "89225070232",
        "address": "г. Москва, ул. Пушкина, д. 10"
    },
    {
        "id": 19,
        "name": "Мария",
        "surname": "Петрова",
        "patronymic": "Александровна",
        "position": "Менеджер",
        "phoneNumber": "89199192843",
        "address": "г. Санкт-Петербург, ул. Ленина, д. 20"
    },
    {
        "id": 20,
        "name": "Андрей",
        "surname": "Сидоров",
        "patronymic": null,
        "position": "Бариста",
        "phoneNumber": "89998887766",
        "address": "г. Екатеринбург, ул. Гагарина, д. 5"
    }
]
```

## Подключение к базе данных

Сервис SberCoffee поддерживает два варианта подключения к базе данных: локальный и удаленный.

### Локальная база данных

Для использования локальной базы данных, установите следующие параметры в файле `application.properties`:

```properties
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:postgresql://localhost:5432/coffee_db
spring.datasource.username=ваше_имя_пользователя
spring.datasource.password=ваш_пароль
spring.datasource.driver-class-name=org.postgresql.Driver
```

### Удаленная база данных

Для использования удаленной базы данных, установите следующие параметры в файле application.properties:

```properties
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:postgresql://94.241.143.82:5432/coffee_db
spring.datasource.username=mpu_admin
spring.datasource.password=test
spring.datasource.driver-class-name=org.postgresql.Driver
Обратите внимание, что в случае удаленной базы данных, параметры spring.datasource.url, spring.datasource.username и spring.datasource.password должны быть настроены в соответствии с вашей конфигурацией удаленной базы данных.
```


Успешного использования сервиса SberCoffee!

