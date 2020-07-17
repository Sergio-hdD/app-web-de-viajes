USE `Sistema-de-viajes`;

/*INSERT INTO user (id,createdat,enabled,password,updatedat,username,photo) VALUES(1, "2020-03-22 00:00:01", 1, "$2a$10$zUXqm9ehdGj7I6f1M8HNRugErdA5SJ06sJUo7dTLty8q7tbKBklPi", "2020-03-22 00:00:01", "empleado","image-1.jpg");
INSERT INTO user (id,createdat,enabled,password,updatedat,username,photo) VALUES(2, "2020-03-22 00:00:01", 1, "$2a$10$wUbGKY0nzIA9aH9gPZuqOuBNhwTmOmzRBZeMk8L8YHCz.AczonfLG", "2020-03-22 00:00:01", "admin", "image-2.jpg");*/
INSERT INTO user (id,createdat,enabled,password,updatedat,username,photo) VALUES(3, "2020-03-22 00:00:01", 1, "$2a$10$SQFFXWS5kg/zUJbhVtjcvuORyzksCDgvhOwjHIA8cv29iGeBQBq66", "2020-03-22 00:00:01", "danymotos", "image-2.jpg");


INSERT INTO user_role (id,createdat,role,updatedat,user_id) VALUES(1, "2020-03-22 00:00:01", "ROLE_USER", "2020-03-22 00:00:01", 1);
INSERT INTO user_role (id,createdat,role,updatedat,user_id) VALUES(2, "2020-03-22 00:00:01", "ROLE_ADMIN", "2020-03-22 00:00:01", 2);

INSERT INTO persona (id_persona,direccion,tel_cel) VALUES(1,"La Calandria 2332, Rafael Calzada",11111111);
INSERT INTO persona (direccion,tel_cel) VALUES("Muñis 2108, Ituzaingo",22222222);
INSERT INTO persona (direccion,tel_cel) VALUES("29 de Septiembre 3456, Lanús",33333333);
INSERT INTO persona (direccion,tel_cel) VALUES("Arenales 256, CABA",44444444);
INSERT INTO persona (direccion,tel_cel) VALUES("Calle 121 563, Beraztegui",55555555);
INSERT INTO persona (direccion,tel_cel) VALUES("Barcala 1298, Ituzaingo ",66666666);
INSERT INTO persona (direccion,tel_cel) VALUES("Tenerife 2174, Ituzaingo",77777777);


INSERT INTO empleado (id_persona,nombre,apellido,tipo_documento,nro_documento) VALUES(1,"Jorge Daniel","Avalos","DNI",11112222);
INSERT INTO empleado (id_persona,nombre,apellido,tipo_documento,nro_documento) VALUES(3,"Daniel Eduardo","Carabajal","DNI",11113333);
INSERT INTO empleado (id_persona,nombre,apellido,tipo_documento,nro_documento) VALUES(4,"Juana","Meneses","DNI",11114444);
INSERT INTO empleado (id_persona,nombre,apellido,tipo_documento,nro_documento) VALUES(5,"Franco","Aguirre","DNI",11115555);
INSERT INTO empleado (id_persona,nombre,apellido,tipo_documento,nro_documento) VALUES(7,"Cristian","Carabajal","DNI",11116666);

INSERT INTO cliente (id_persona,razon_social,cuit) VALUES(2,"Muñoz Hogar","30-11111111-2");
INSERT INTO cliente (id_persona,razon_social,cuit) VALUES(6,"Z-motos","33-11111111-6");


INSERT INTO viaje (id_viaje,fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES(1,'2020-05-17',"Arredondo 2345","Sourigues",1,2,790,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-17',"San Martín 345","Florencio Varela",1,2,750,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-18',"San Martín 345","Florencio Varela",3,2,750,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-18',"Mitre 678","Quilmes",1,6,700,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-18',"Sarmiento 781","CABA",1,2,600,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-18',"Av. Mitre 2589","Avellaneda",1,6,650,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-20',"De la Canal 1234","Esteban Echevería",3,6,780,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-20',"Azucena 345","Rafael Calzada",1,6,790,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-21',"San Martín 1345","Arogué",1,6,850,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-21',"San Martín 345","Florencio Varela",4,6,750,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-21',"Mitre 678","Quilmes",4,2,700,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-21',"Sarmiento 781","CABA",1,6,600,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-25',"Av. Mitre 2589","Avellaneda",3,2,650,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-25',"De la Canal 1234","Esteban Echevería",4,6,780,0," ");
INSERT INTO viaje (fecha,direccion,localidad,empleado_id_persona,cliente_id_persona,importe,contado,detalle) VALUES('2020-05-25',"Azucena 345","Rafael Calzada",1,2,790,0," ");