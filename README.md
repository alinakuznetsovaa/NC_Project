# Тема проекта: Приложение для онлайн записи на процедуры к мастерам

## _Дизайн приложения:_
* ___Регистрация (по почте)___
    * _Для мастера_
        * ФИО, адрес студии, email
		* Список услуг (со временем выполнения процедуры) с выбором категории услуг (массаж, ногти, и тд)

	* _Для клиента_
		* ФИО, email

* ___Авторизация (по почте)___
	* _Для клиента_
	* _Для мастера_

* ___Главная страница___
	* _Поиск услуги_ 
		* Отображение категорий услуг при поиске
		* Выбор даты
		* Отображение подходящих мастеров после поиска
	* _Запись_
		* Выбор мастера, услуг, времени 
		* Формирование записи со статусом: _«ожидает подтверждения»_
		

* ___Личный кабинет___
	* _Список предстоящих сеансов_
		* Изменение статуса записи на _«подтвержден»_ (после подтверждения мастера)
		* Изменить сеанс (дату и время)
		* Отменить сеанс

	* _Список сеансов прошедших_
		* Оценить и оставить отзыв

* ___Рассылка акций по почте___

***
## _Схема базы данных:_
___Client table___             
Colomns | Types                
:---------|:------:            
__CLIENT_ID__ | __NUMBER__
C_FIRST_NAME | VARCHAR
C_LAST_NAME | VARCHAR
C_EMAIL | VARCHAR

___Master table___ 
Colomns | Types
:---------|:------:
__MASTER_ID__ | __NUMBER__
M_FIRST_NAME | VARCHAR
M_LAST_NAME | VARCHAR
M_EMAIL | VARCHAR
ADRESS | VARCHAR

___Category table___
Colomns | Types
:---------|:------:
__CATEGORY_ID__ | __NUMBER__
CATEGORY_TITLE | VARCHAR

___Service table___
Colomns | Types
:---------|:------:
__SERVICE_ID__ | __NUMBER__
_CATEGORY_ID_ | _NUMBER_
SERVICE_TITLE | VARCHAR
TIME | NUMBER

___Record table___
Colomns | Types
:---------|:------:
__RECORD_ID__ | __NUMBER__
_CLIENT_ID_ | _NUMBER_
_MASTER_ID_ | _NUMBER_
_SERVICE_ID_ | _NUMBER_
DATE_START | DATE
DATE_END | DATE
