## Шаблон Композит / Composite Task03 ##

Необходимо создать приложение, разбирающее текст из учебника по программированию из файла и позволяющее выполнять с текстом две различных операции

# Требования #

* Разобранный текст должен быть представлен в виде объекта (текста), содержащего, например, предложения и блоки кода, предложение может содержать слова предложения. Слова предложения (части предложения), могут быть, например, простыми словами и знаками препинания. Данные классы являются классами сущностей и не должны быть перегружены методами логики.
* Разобранный текст необходимо восстановить в первоначальном виде, за исключением пробелов между элементами. Пробелы и знаки табуляции при разборе могут заменяться одним пробелом.
* Для деления текста на предложения и другие составляющие следует использовать регулярные выражения. Не забывать, что регулярные выражения для приложения являются литеральными константами.
* Код, выполняющий разбитие текста на составляющие части, следует оформить в виде классов-парсеров.
* При разработке парсеров, разбирающих текст, необходимо следить за количеством создаваемых объектов-парсеров.
* При реализации задания можно использовать шаблоны Composite и Chain of Responsibility.
* При обработке исключительных ситуаций приложение необходимо использовать логгер Log4j.
* Оформление кода должно соответствовать Java Code Convention.
* Созданное приложение должно позволять реализовывать группу задач по работе над текстом (задачи приведены ниже) без “переписывания” существующего кода.

# Функциональные возможности #

* Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.
* В каждом предложении текста поменять местами первое слово с последним, не изменяя длины предложения.