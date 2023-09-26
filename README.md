# SberCoffee Backend Service

UPD: JavaDoc �������� �� [������](https://github.com/DmitriiDerendyaev/SberCoffee/blob/main/documentation/index.html)
UPDD: � ����� refactor ����� ������� ��������� � ����������� ����

����� ���������� � ������-������ ������-������� SberCoffee �� ���������! ���� ������ ������������� API ��� ���������� ���������, ������������, �������� ����, �������� � ��������� �������. �� ������ ������������ ���� ������ ��� ���������� ����� �������� � �������������� ������-������� ��������.

## ��������� �������

����� ������� ������� ������������ �������� �� [������](http://localhost:8080/swagger-ui/index.html) � �������������� Swagger

### Client Controller

| ������                        | ��������                                   |
| ------------------------------ | ------------------------------------------ |
| GET /clients                   | �������� ������ ���� ��������.             |
| GET /clients/{id}             | �������� ���������� � ������� �� ID.      |
| POST /clients                 | ������� ������ �������.                   |
| PUT /clients/{id}             | �������� ���������� � ������� �� ID.      |
| DELETE /clients/{id}          | ������� ������� �� ID.                    |

### Position Controller

| ������                        | ��������                                   |
| ------------------------------ | ------------------------------------------ |
| GET /positions                 | �������� ������ ���� ��������� ���������� �����������. |
| GET /positions/{id}           | �������� ���������� � ��������� ���������� �� ID.      |
| POST /positions               | ������� ����� ��������� ����������.                   |
| PUT /positions/{id}           | �������� ���������� � ��������� ���������� �� ID.      |
| DELETE /positions/{id}        | ������� ��������� ���������� �� ID.                    |

### Coffee Order Controller

| ������                        | ��������                                   |
| ------------------------------ | ------------------------------------------ |
| GET /coffee-orders             | �������� ������ ���� ������� ����.         |
| GET /coffee-orders/{id}       | �������� ���������� � ������ ���� �� ID.  |
| POST /coffee-orders           | ������� ����� ����� ����.                 |
| PUT /coffee-orders/{id}       | �������� ���������� � ������ ���� �� ID.  |
| DELETE /coffee-orders/{id}    | ������� ����� ���� �� ID.                  |

### Item Controller

| ������                        | ��������                                   |
| ------------------------------ | ------------------------------------------ |
| GET /items                     | �������� ������ ���� ��������� ������� (���� ���� � �� ����). |
| GET /items/{id}               | �������� ���������� � ������ �� ID.       |
| POST /items                   | �������� ����� ����� (��� ����) � �����������.              |
| PUT /items/{id}               | �������� ���������� � ������ �� ID.       |
| DELETE /items/{id}            | ������� ����� �� ������������ �� ID.       |

### Status Controller

| ������                        | ��������                                   |
| ------------------------------ | ------------------------------------------ |
| GET /statuses                 | �������� ������ ���� ��������� �������� �������. |
| GET /statuses/{id}           | �������� ���������� � ������� ������ �� ID.      |
| POST /statuses               | ������� ����� ������ ������.                   |
| PUT /statuses/{id}           | �������� ���������� � ������� ������ �� ID.      |
| DELETE /statuses/{id}        | ������� ������ ������ �� ID.                    |

### Staff Controller

| ������                        | ��������                                   |
| ------------------------------ | ------------------------------------------ |
| GET /staff                    | �������� ������ ���� �����������.           |
| GET /staff/{id}               | �������� ���������� � ���������� �� ID.    |
| POST /staff                   | ������� ������ ����������.                 |
| PUT /staff/{id}               | �������� ���������� � ���������� �� ID.    |
| DELETE /staff/{id}            | ������� ���������� �� ID.                  |

## ������� ������������� ������ � JSON

### ������� (Client)

```json
[
  {
    "id": 3,
    "name": "����",
    "surname": "������",
    "patronymic": "��������",
    "phoneNumber": "81234567890",
    "address": "��. ���������, 123",
    "email": "ivan@example.com",
    "birthday": "1990-05-15"
  },
  {
    "id": 4,
    "name": "�����",
    "surname": "�������",
    "patronymic": "���������",
    "phoneNumber": "89876543210",
    "address": "��. ��������, 456",
    "email": "maria@example.com",
    "birthday": "1985-08-20"
  },
  {
    "id": 5,
    "name": "�������",
    "surname": "�������",
    "patronymic": "����������",
    "phoneNumber": "85555555555",
    "address": "��. ��������� ������, 789",
    "email": "alex@example.com",
    "birthday": "1978-03-10"
  }
]
```

### ������ ���� (CoffeeOrder)
```json
[
  {
    "id": 11,
    "client": {
      "id": 3,
      "name": "����",
      "surname": "������",
      "patronymic": "��������",
      "phoneNumber": "81234567890",
      "address": "��. ���������, 123",
      "email": "ivan@example.com",
      "birthday": "1990-05-15"
    },
    "item": {
      "id": 3,
      "name": "��������",
      "price": 150.0
    },
    "quantity": 2,
    "status": {
      "id": 5,
      "name": "������"
    },
    "staff": {
      "id": 18,
      "name": "���������",
      "surname": "�������",
      "patronymic": "���������",
      "position": "��������",
      "phoneNumber": "89225070232",
      "address": "�. ������, ��. �������, �. 10"
    },
    "startTime": "2023-09-22T10:52:21.072094",
    "total": 300.00
  }
]
```

### ������ (Item)

```json
[
  {
    "id": 3,
    "name": "��������",
    "price": 150.0
  },
  {
    "id": 4,
    "name": "�����",
    "price": 130.0
  },
  {
    "id": 5,
    "name": "��������",
    "price": 100.0
  }
]
```

### ��������� ����������� (Position)
```json
[
  {
    "id": 4,
    "name": "�������",
    "description": "�������������� ���� � ����������� ��������"
  },
  {
    "id": 5,
    "name": "��������",
    "description": "��������� ������"
  },
  {
    "id": 6,
    "name": "��������",
    "description": "�������� ����"
  }
]
```

### ������� ������� (Status)

```json
[
  {
    "id": 5,
    "name": "������"
  },
  {
    "id": 6,
    "name": "� ��������"
  },
  {
    "id": 7,
    "name": "��������"
  }
]
```

### ���������� (Staff)
```json
[
    {
        "id": 18,
        "name": "���������",
        "surname": "�������",
        "patronymic": "���������",
        "position": "��������",
        "phoneNumber": "89225070232",
        "address": "�. ������, ��. �������, �. 10"
    },
    {
        "id": 19,
        "name": "�����",
        "surname": "�������",
        "patronymic": "�������������",
        "position": "��������",
        "phoneNumber": "89199192843",
        "address": "�. �����-���������, ��. ������, �. 20"
    },
    {
        "id": 20,
        "name": "������",
        "surname": "�������",
        "patronymic": null,
        "position": "�������",
        "phoneNumber": "89998887766",
        "address": "�. ������������, ��. ��������, �. 5"
    }
]
```

## ����������� � ���� ������

������ SberCoffee ������������ ��� �������� ����������� � ���� ������: ��������� � ���������.

### ��������� ���� ������

��� ������������� ��������� ���� ������, ���������� ��������� ��������� � ����� `application.properties`:

```properties
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:postgresql://localhost:5432/coffee_db
spring.datasource.username=����_���_������������
spring.datasource.password=���_������
spring.datasource.driver-class-name=org.postgresql.Driver
```

### ��������� ���� ������

��� ������������� ��������� ���� ������, ���������� ��������� ��������� � ����� application.properties:

```properties
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:postgresql://94.241.143.82:5432/coffee_db
spring.datasource.username=mpu_admin
spring.datasource.password=test
spring.datasource.driver-class-name=org.postgresql.Driver
�������� ��������, ��� � ������ ��������� ���� ������, ��������� spring.datasource.url, spring.datasource.username � spring.datasource.password ������ ���� ��������� � ������������ � ����� ������������� ��������� ���� ������.
```


��������� ������������� ������� SberCoffee!

