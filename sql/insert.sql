use caducidad;
SELECT tipo, nombre_producto FROM productos;
select *from Usuario;
select *from productos;
DELETE FROM productos WHERE id_producto =(11);
select  tipo from productos;
DELETE FROM productos WHERE id_producto = "3";
delete from productos where id_producto in (4,5,6,7,8,9);
insert into Usuario (correo,contraseña) values (
'pruebas',
'1234'
);
UPDATE Usuario SET correo = 'noe', contraseña = '2308' WHERE id_usuario = 1;


select * from Usuario where id_usuario = "1";

/*chequeo de productos*/
SELECT * FROM productos WHERE id_usuario = 1;

/*eliminar */
delete from productos where productos.id_usuario= '1';
select *from productos;


/*saber donde se conecto*/
select * from Usuario, productos where Usuario.correo=('admin') and Usuario.contraseña=('emilioswer') and Usuario.id =('1') 
and productos.id_usuario = usuario.id; 

SELECT * FROM productos WHERE id_usuario = '1';

insert into productos (cantidad,nombre_producto,tipo,fecha,id_usuario) values(
'1111','alimento','enlatado','25/06/23','1'
);

select *from productos;
select *from productos where tipo = ('enlatado') and cantidad = ('1') and nombre_producto = ("alimento de gato")  and fecha = ("2025-06-23") ;

SELECT tipo, cantidad, nombre_producto, fecha FROM productos WHERE id_usuario = 1;
select * from productos where id_usuario=1;