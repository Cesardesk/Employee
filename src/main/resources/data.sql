insert into position (id, name) values (101, 'Position 1'),
                                       (102, 'Position 2'),
                                       (103, 'Position 3');

insert into person (id, name, last_name, address, cellphone, city_name) values 
	(101, 'Name 5', 'Last name 19', 'Street 1111 av 1', '320-321321', 'Bogotá'),
	(102, 'Name 3', 'Last name 18', 'Street 11 av 1', '320-321321', 'Medellin'),
	(103, 'Name 7', 'Last name 17', 'Street 111 av 1', '320-321321', 'Bogotá'),
	(104, 'Name 1', 'Last name 16', 'Street 12 av 1', '320-321321', 'Pereira'),
	(105, 'Name 2', 'Last name 15', 'Street 13 av 1', '320-321321', 'Cúcuta'),
	(106, 'Name 8', 'Last name 14', 'Street 14 av 1', '320-321321', 'Bogotá'),
	(107, 'Name 4', 'Last name 13', 'Street 15 av 1', '320-321321', 'Bucaramanga'),
	(108, 'Name 6', 'Last name 12', 'Street 17 av 1', '320-321321', 'Bogotá');
	
insert into employee(id, person_id, position_id, salary) values 
	(101,105,103,2800000),
	(102,106,102,5000000),
	(103,107,101,2500000),
	(104,108,103,3500000),
	(105,101,102,1800000),
	(106,102,101,550000),
	(107,103,103,3250000),
	(108,104,102,5200000);