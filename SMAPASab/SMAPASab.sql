create database SMAPASab
use SMAPASab
create table ruta
	(cveRuta char(1) NOT NULL,
	 descripcion char(4),
	 constraint rutapk primary key (cveRuta));
	 
/*create table tarifa
	(cveTar char(1) NOT NULL,
	 costo smallint default 0,
	 constraint tarifapk primary key (cveTar));*/
	 
create table usuario 
	(cuenta varchar(6) not null,
	 nombre varchar (15) not null,
	 apellidos varchar (20) default 'S/N',
	 domicilio varchar (25) default 'S/N',
	 numero varchar (4) default 'S/N',
	 cveTar char(1),
	 cveRuta char(1),
	 telefono varchar(10),   /*telefono, en realidad es el giro*/
	 fInst date default GetDate(),
	 constraint userpk primary key (cuenta),
	 constraint userfk1 foreign key (cveTar) references cuotas(cvecuota),
	 constraint userfk2 foreign key (cveRuta) references ruta(cveRuta));
	 
create table recargo
	(cveRecargo char(1) not null,
	 descrip char(4),
	 costo decimal (4,2),
	 constraint recafk1 primary key (cveRecargo));
 
create table pago
	(cuenta varchar(6) not null,
	 fEmision date,
	 fVencimiento date,
	 fUltimPago date,
	 lectAnte int not null default 0,
	 lectAct int,
	 constraint pagok primary key (cuenta),
	 constraint pagofk0 foreign key (cuenta) references usuario(cuenta));
	 
create table cuotas
	(cvecuota char(1) not null,
	 descrip varchar(4),
	 max1 smallint,
	 max2 smallint,
	 max3 smallint,
	 max4 smallint,
	 tari1 decimal (6,2),
	 tari2 decimal (6,2),
	 tari3 decimal (6,2),
	 tari4 decimal(6,2),
	 constraint cuotaspk primary key (cvecuota));

create table instalación
	(cuenta varchar(6) not null,
	 costo smallint,
	 revision char(1),
	 constraint instk primary key (cuenta),
	 constraint instfk0 foreign key (cuenta) references usuario(cuenta));
	 
create table anticipo
	(cuenta varchar(6) not null,
	 tipo varchar(1),
	 fTermino date,
	 activo varchar(1),
	 constraint antipk primary key (cuenta),
	 constraint antifk0 foreign key (cuenta) references usuario(cuenta));

create table recibo
	(numero smallint,
	  constraint recibopk primary key (numero));

insert into ruta
values ('1','2701'),
	   ('2','2702');

insert into cuotas
values('1','dome',25,50,150,999,100,3.00,4,5),
	  ('2','come',20,50,100,999,110,5.00,6.00,7),
	  ('3','indu',20,50,100,999,110,5,6,7.00),
	  ('4','fija',90,20,5,10,0,0,0,0);
	  
insert into recargo
values (0,'reca',18.90),
	   (1,'coop',5.00); 
	   
insert into recibo
values(10);	   
	   

/*select * from recibo	 update recibo set numero=numero+1   
insert into anticipo
values('0023.3','1',DATEADD(mm,6,GETDATE()),1),
	  ('0023.4','2',DATEADD(mm,12,GETDATE()),1);
	  select * from anticipo
	  select * from instalación
	  update anticipo set fTermino=DATEADD(mm,6,GETDATE()),activo=1
	  
	  select * from anticipo
	   select * from usuario
	   select * from pago
	   select cveRuta from usuario
	   
	   select * from pago where cuenta in (select cuenta from usuario where cveRuta='2')
	   select * from pago
	   select * from anticipo delete from anticipo
	   update pago set fEmision='10-10-14', fVencimiento='11-09-14' where cuenta in (select cuenta from usuario where cveRuta='1')
	   
insert into usuario
values ('0216.2','Angel','Molina Morelos','Jaral del Progreso','10','1','2','4661111111','11-8-2014'),
	   ('0023.4','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
	   ('0523.4','Juan Angel','Perez Rojas','Allende','109','2','1','4651111111',GetDate());
	   select * from usuario
	   select * from cuotas
	   insert into usuario
values ('0216.3','Angel','Molina Morelos','Jaral del Progreso','10','1','2','4661111111','11-8-2014'),
		('0216.4','Angel','Molina Morelos','Jaral del Progreso','10','1','2','4661111111','11-8-2014'),
		('0216.5','Angel','Molina Morelos','Jaral del Progreso','10','1','2','4661111111','11-8-2014'),
		('0216.6','Angel','Molina Morelos','Jaral del Progreso','10','1','2','4661111111','11-8-2014'),
		('0216.7','Angel','Molina Morelos','Jaral del Progreso','10','1','2','4661111111','11-8-2014'),
		('0216.8','Angel','Molina Morelos','Jaral del Progreso','10','1','2','4661111111','11-8-2014'),
		('0216.9','Angel','Molina Morelos','Jaral del Progreso','10','1','2','4661111111','11-8-2014'),
		('0025.1','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0025.2','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0025.3','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0025.4','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0025.5','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0025.6','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0025.7','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0025.8','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0025.9','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0026.1','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0026.2','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0026.3','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0026.4','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0026.5','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0026.6','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0026.7','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0026.8','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0026.9','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0027.1','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0027.2','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0027.3','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0027.4','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0027.5','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0027.6','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0027.7','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0027.8','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
		('0027.9','Juan Antonio','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate());
	  
	  select * from anticipo
	  
	  select r.descripcion rut, u.cuenta cue, u.nombre name, u.apellidos app, u.cveTar tar, p.lectAnte lan, p.lectAct lac 
	  from usuario u inner join pago p on u.cuenta=p.cuenta inner join ruta r on r.cveRuta=u.cveRuta 
	  where u.cveRuta=1 and DATEDIFF(MM,p.fEmision,GETDATE())>2 order by u.cuenta
	  and YEAR(p.fEmision) !=YEAR(GETDATE()) MONTH(p.fEmision) !=MONTH(GETDATE())
	  
	  select * from pago select * from usuario where cuenta=''
	  update pago set lectAct=230, fEmision=GETDATE() where cuenta='0023.3'
	  update pago set fEmision='11-08-14' where cuenta='0023.3'
	  select * from anticipo
	  update anticipo set activo=1 where cuenta='0023.3'
	  
	  
select usuario.nombre+'-'+usuario.apellidos name,usuario.cuenta cue,cuotas.cvecuota tar
from usuario inner join cuotas on cuotas.cvecuota=usuario.cveTar
where usuario.cuenta='0023.3' 
select * from usuario
	  select * from cuotas
insert into instalación
values ('0216.2',100,'1');
select u.cuenta cue,u.nombre name,u.cveTar tar,r.descripcion rut,u.apellidos app from usuario u inner join ruta r on r.cveRuta=u.cveRuta where not exists (select * from pago where u.cuenta=pago.cuenta) and u.cveRuta='2'	  
select * from pago where u.cuenta=pago.cuenta
	  
	  
	  insert into anticipo values('0023.5','1',DATEADD(mm,6,GETDATE()),1);
	  update anticipo set activo=2 where fTermino <= GETDATE()
	  update anticipo set fTermino='10-09-14' where cuenta='0023.3'
	  
	  select * from anticipo
	  
select nombre name,apellidos apell from usuario
	  select COUNT(cuenta) cue from usuario
	  select u.cuenta cue, u.cveTar rut, u.nombre name, u.apellidos ape, p.lectAnte ante,p.lectAct act, DATEDIFF (mm,p.fEmision,GETDATE()) bim, DATEDIFF (mm,p.fUltimPago,p.fEmision) ult
	  from usuario u inner join pago p on p.cuenta=u.cuenta
	  where u.nombre='angel' and apellidos='molina morelos' select * from pago inner join usuario on pago.cuenta=usuario.cuenta 
	  where usuario.cuenta='0023.5' select * from cuotas
	  select * from pago
	  update pago set fEmision='01-06-14'
	  
	  select * from usuario    select u.nombre name,u.apellidos apell from usuario u inner join pago p on p.cuenta=u.cuenta
	  select COUNT(u.cuenta) cue from usuario u inner join pago p on p.cuenta=u.cuenta
	  /*if (select DATEDIFF (mm,fEmision,GETDATE()) from pago)>2
			2*(select tari1 from cuotas)
	  
	  
	  select DATEDIFF (mm,fEmision,GETDATE()) from pago*/
	  
	  select * from usuario select COUNT(*) p from pago select * from pago
	  
	  select * from pago select * from usuario
	  select * from instalación where cuenta='0023.6'
	  select * from cuotas select costo from recargo where cveRecargo='0'
	  delete from usuario where cuenta='0023.6'
	  delete from usuario where cuenta='0023.6'
	  select * from instalación select * from usuario select * from pago
	  select * from instalación where cuenta='0023.7'
	  delete from instalación where cuenta=''
	  where cvecuota='1'
	  select cveTar from usuario where cuenta='0023.3'
	  
	  select u.cuenta cue, u.nombre name, u.cveTar rut, u.apellidos ape, p.lectAnte ante,p.lectAct act, DATEDIFF (mm,p.fEmision,GETDATE()) bim, DATEDIFF (mm,p.fUltimPago,p.fEmision) ult from usuario u inner join pago p on p.cuenta=u.cuenta
	  
	  select usuario.nombre+' '+ usuario.apellidos name,usuario.domicilio domic, usuario.cuenta cue, usuario.telefono cont, usuario.cveTar,usuario.domicilio dom,pago.fUltimPago ult,MONTH(GETDATE()) mes, YEAR(getdate()) año, DIFFERENCE(pago.lectAct,pago.lectAnte) con from usuario inner join pago on pago.cuenta=usuario.cuenta where pago.cuenta='0023.3'
	  select usuario.nombre+' '+ usuario.apellidos name,usuario.domicilio domic, usuario.cuenta cue, MONTH(GETDATE()) mes, YEAR(getdate()) año, usuario.telefono cont, usuario.cveTar,usuario.domicilio dom,pago.fUltimPago ult, DIFFERENCE(pago.lectAct,pago.lectAnte) con from usuario inner join pago on pago.cuenta=usuario.cuenta where pago.cuenta='0023.3'
	  
	  insert into usuario
	  values ('1006.2','Wats','Molina Morelos','Jaral del Progreso','10','1','2','4661111111','11-8-2014'),
			  ('1006.3','Juan Mario','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
			  ('1006.4','Toño','Martinez Ramirez','Hidalgo','S/N','2','2','4151111111',GetDate()),
			  ('1006.5','Dani','Perez Rojas','Allende','109','2','1','4651111111',GetDate());
	  
	  insert into instalación
	  values ('1006.2',120,2), ('1006.3',120,2);
	  select * from instalación where cuenta='0023.7' select * from instalación inner join pago on pago.cuenta=instalación.cuenta
	  
	  update instalación set revision=2 where cuenta='0023.7'
	  
	  insert pago
	  values('1006.2','11-11-11','11-12-11','11-11-10',200,200),('1006.4','11-11-11','11-12-11','11-11-10',200,200);
	  
	  select u.cuenta cue, u.nombre name, u.cveTar rut, u.apellidos ape, p.lectAnte ante,p.lectAct act, DATEDIFF (mm,p.fEmision,GETDATE()) bim, DATEDIFF (mm,p.fUltimPago,p.fEmision) ult from usuario u inner join pago p on p.cuenta=u.cuenta where nombre='ana maria' select * from usuario
	  
select * from pago	update pago set /*fUltimPago='11-11-13',*/fEmision='01-07-14'/*,fVencimiento='13-10-14'*/  
where cuenta='0023.3'
select u.cuenta,u.nombre,u.cveTar,r.descripcion,u.apellidos from usuario u inner join ruta r on r.cveRuta=u.cveRuta where not exists (select * from pago where u.cuenta=pago.cuenta) and u.cveRuta='2'

select cuenta cue,nombre name,cveTar tar,cveRuta rut, apellidos app from usuario where not exists (select * from pago where usuario.cuenta=pago.cuenta)
	   
select * from pago where cuenta in (select pago.cuenta, lectAct - lectAnte from pago ) 	   
	  select * from pago 
	  
	  select usuario.cveRuta rut, usuario.cuenta cue, usuario.nombre name, usuario.apellidos app, pago.fUltimPago ult, pago.lectAct act, pa
	  select * from instalación select * from pago
	  select DATEDIFF (mm,p.fEmision,GETDATE()) bim, r.descripcion ruta, u.cuenta cue, u.nombre name, u.cveTar rut, u.apellidos ape, p.lectAnte ante,p.lectAct act, DATEDIFF (mm,p.fEmision,GETDATE()) bim, DATEDIFF (mm,p.fUltimPago,p.fEmision) ult from usuario u inner join pago p on p.cuenta=u.cuenta inner join ruta r on r.cveRuta=u.cveRuta where u.cveRuta='1' order by u.cuenta 
	  select usuario.cveRuta, usuario.cuenta,usuario.nombre from pago inner join usuario on usuario.cuenta=pago.cuenta where usuario.cveRuta='2'
	  select fUltimPago pago from pago where cuenta='0023.3'
	  update pago set fUltimPago='' where cuenta=''
	  select * from pago
	  
	  select r.descripcion rut, u.domicilio dom, u.cuenta cue, u.nombre name, u.cveTar tar, u.apellidos ape, p.lectAnte ante,p.lectAct act, DATEDIFF (mm,p.fEmision,GETDATE()) bim, DATEDIFF (mm,p.fUltimPago,p.fEmision) ult from usuario u inner join pago p on p.cuenta=u.cuenta inner join ruta r on r.cveRuta=u.cveRuta order by u.domicilio
	  
	  
	  
select * from usuario /*where cuenta='0205.2' select * from pago where cuenta='0205.2'*/

select usuario.cuenta from pago inner join usuario on usuario.cuenta=pago.cuenta
select u.cuenta Cue, u.nombre Name, u.domicilio Adre,u.apellidos Apell, u.cveRuta rut, p.lectAct Ante from pago p inner join usuario u on u.cuenta=p.cuenta where p.cuenta='0023.4' and u.cveRuta='2'
select cuenta,nombre,apellidos,domicilio,cveRuta from usuario where cuenta='0023.3'
select * from pago
update pago
set lectAct=108
where cuenta='0023.5'

select r.descripcion rut, u.cuenta cue, u.apellidos app, u.nombre name, u.cveTar tar, p.lectAnte lan, p.lectAct lac
from usuario u inner join pago p on u.cuenta=p.cuenta inner join ruta r on r.cveRuta=u.cveRuta 
where u.cveRuta=2 and YEAR(p.fEmision) !=YEAR(GETDATE()) and MONTH(p.fEmision) !=MONTH(GETDATE())  /*datepart(mm, p.fEmision) != datepart(mm, getdate()) */
order by u.cuenta
select * from pago
insert into pago 
values	('0216.2','11-11-11','18-12-11','01-09-14',0,298),
		('0023.4','18-12-11','09-01-12','01-09-14',150,198),
		('0523.4','11-11-11','02-01-12','01-09-14',105,230);
		select u.cuenta Cue, u.nombre Name, u.domicilio Adre,u.apellidos Apell, u.cveRuta rut, p.lectAct Ante 
		from pago p inner join usuario u on u.cuenta=p.cuenta where p.cuenta='0052.3'
		select * from usuario 
select * from pago		
	
	update pago set fUltimPago=GETDATE(),lectAnte=lectAct where cuenta='0216.2'

select r.descripcion rut, u.cuenta cue, u.nombre name, u.apellidos ape, u.cveTar tar, u.domicilio doc 
from usuario u inner join ruta r on r.cveRuta=u.cveRuta		
order by doc
		select * from cuotas
select COUNT(*)
from usuario
where cuenta='0005.0'

select usuario.cuenta,usuario.nombre,usuario.apellidos, tarifa.cveTar, ruta.cveRuta, usuario.domicilio doc,usuario.numero num, usuario.telefono tel
from usuario inner join tarifa on usuario.cveTar=tarifa.cveTar inner join ruta on usuario.cveRuta=ruta.cveRuta
where usuario.cuenta='0005.2'

update usuario
set nombre='Mario Miguel', apellidos='Molina Castañeda', domicilio='Jaral del Progreso',numero='102',cveRuta='1',cveTar='2',telefono='4661111111'
where cuenta='0005.2'

select * from usuario where cuenta='0005.2'
select * from pago where cuenta='0005.2'

delete from pago where cuenta='0005.2' delete from usuario where cuenta='0005.2'

select r.descripcion rut, u.cuenta cue, u.nombre name, u.cveTar tar, u.domicilio doc, u.apellidos ape
from usuario u inner join ruta r on r.cveRuta=u.cveRuta
where u.cveRuta=1

tarifa t inner join on t.cveTar=u.cveTar

select * from pago

insert into usuario values ('0005.2','Mario','Castañeda Molina','Jaral del P.','10','1','2','4661111111'); 
insert into pago values	(0456,'0005.2','11-11-11','18-12-11',0,298.50,0,1)
select * from usuario
select * from pago
select MAX(cuenta)cue from usuario
select MAX(noRecibo) recibo from pago

update cuotas
set max1=25,max2=50,max3=150,max4=999,tari1=100,tari2=3.10,tari3=4,tari4=5
where cvecuota='01'

select * from cuotas
select * from fija

select * from recargo where cveRecargo=0
update recargo set costo=18.50 where cveRecargo=0

update fija 
set costo=90.00, drenaje=15, interes=3,IVA=12.00
where cvefija='04'
