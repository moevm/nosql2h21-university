# Нереляционная модель данных

## Сущность "абитуриент" (enrollee)
- Email (String) (ключ) – почта абитуриента
- Firstname (String) – имя абитуриента
- Lastname (String) – фамилия абитуриента
- Patronymic (String) – отчество абитуриента
- Password (String) – пароль
- DOB (Date) – Дата рождения
- School (String) – школа
- City (String) – город
- EGEresults (Array[Object]) – результаты ЕГЭ (предмет и количество баллов)
- IndividualAchievements (Array) – индивидуальные достижения (Золотая медаль, ГТО и др.)

## Сущность "работник ВУЗа" (employees)
- Email (String) – почта работника
- Firstname (String) – имя работника
- LastName (String) – фамилия работника
- Patronymic (String) – отчество работника
- Password (String) – пароль
- DOB (Data) – дата рождения
- UniversityId (String) – id прикрепленного унивеситета

## Сущность "университет" (university)
- Name (String) – название ВУЗа
- City (String) – город 
- Contacts (Object) – контакты ВУЗа (почта и телефон)

## Оценка удельного объема информации, хранимой в моделях

Пусть у нас совокупность полей (email, firstname, lastname, patronymic, password, DOB, universityId) сущности "Работник ВУЗа" занимает N памяти, совокупность полей (name, city) сущности "ВУЗ" занимает M памяти и совокупность полей (email, phone) сущности "Контакты" занимает P памяти. Так же имеем среднее количество работников H и количество ВУЗов U.

Тогда хранение данных о ВУЗе, работнике и контактах в MongoDB будет занимать U * (H * M + N + P)

Тогда при условии N = (согласно полям: 25 байт + 7 байт + 9 байт + 10 байт + 10 байт + 8 байт + 15 байт = 84 байт), M = (согласно полям: 30 байт + 10 байт = 40 байт) и P = (согласно полям: 25 + 12 байт = 37 байт). В среднем имеем H = 3 работника и имеем U = 300 вузов.

Для хранения данных о ВУЗе, работнике и контактах нам понадобится 300 * (3 * 40 + 84 + 37) = 72 300 байт

Пусть у нас совокупность полей (email, firstname, lastname, patronymic, password, DOB, school, city) сущности "Абитуриент" занимает E, совокупность полей (subject, score) сущности "Результаты ЕГЭ" занимает S, и каждый объект в "Индивидуальных достижениях" занимает I памяти. Так же имеем, что среднее количество "Индивидуальных достижений" W, среднее количество "Результатов ЕГЭ" R, и количество "Абитуриентов" V.

Тогда хранение данных об абитуриентах в MongoDB будет занимать 
V * (E + S * R + I * W)

Тогда при условии E = (25 байт + 7 байт + 9 байт + 10 байт + 10 байт + 8 байт + 15 байт + 10 байт = 94 байт), S = (8 байт + 2 байт = 10 байт) и I = 10 байт. В среднем имеем W = 2, R = 3 и количество абитуриентов V = 6 000.

Для хранения данных об абитуриентах нам понадобится 6 000 * (94 + 10 * 3 + 10 * 2) = 864 000 байт

Всего хранение модели будет занимать 
U * (H * M + N + P) + V * (E + S * R + I * W) = 936 300 байт

## Примеры запросов

 * Добавление абитуриента
db.enrolles.insertOne({email: "someemail@mail.ma", firstName: "Ivan", lastName: "Ivanov", patronymic: "Ivanovich", password: "some_pass", DOB: "2000-01-01", school: "some school 1", city: "SPb", egeResults:[{subject:"rus", score:43}], individualAchievenents:["GTO"]})

* Добавление ВУЗа
db.universities.insertOne({name: "LETI", city: "SPb", contacts: {email: "someEmail@mail.ma", phone: "+7-983-321-23-43"}})

* Добавление работника
db.employees.insertOne({email: "someEmail@mail.ma", firstName: "Ivan", lastName: "Ivanov", patronymic:"Ivanovich", password:"some_pas", DOB: "1980-01-01", universityId:"6173d3fbc85dd2433f25f039"})

* Поиск ВУЗа
db.universities.find({_id: ObjectId("6173cf0c985faf33df8ea21b")}) 
* Удаление ВУЗа
db.universities.remove({_id: ObjectId("6173cf0c985faf33df8ea21b")})

