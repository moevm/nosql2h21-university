# Нереляционная модель данных

## Сущность "абитуриент" (enrollee)
- Email (String) (ключь) – почта абитуриента
- Firstname (String) – имя абитуриента
- Lastname (String) – фамилия абитуриента
- Patronymic (String) – отчество абитуриента
- Password (String) – пароль
- DOB (Date) – Дата рождения
- School (String) – школа
- City (String) – город
- EGEresults (Array) – результаты ЕГЭ
- IndividualAchievments (Array) – индивидуальные достижения (Золотая медаль, ГТО и др.)

## Сущность "результаты ЕГЭ" (EGEresults)
- Subject (String) – название предмета
- Score (Int) – количество баллов

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
- Contacts (DBref) – ссылка на сущность "контакты" ВУЗа

## Сущность "контакт" (contact)
- Email (String) – почта
- Phone (String) – номер телефона
