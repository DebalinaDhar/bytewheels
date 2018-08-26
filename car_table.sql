CREATE schema bytewheels;
USE bytewheels;

CREATE table car(
id int not null,
model_name varchar(45) not null,
category varchar(45) not null,
cost_per_day int not null,
serial_number varchar(45) not null,
primary key(id)
);


insert into
car values(1,'Ford Fiesta','Compact',20,'FORD-FIESTA-COMPACT-1');
insert into
car values(2,'Ford Fiesta','Compact',20,'FORD-FIESTA-COMPACT-2');
insert into
car values(3,'Ford Focus','Compact',20,'FORD-FOCUS-COMPACT-1');
insert into
car values(4,'Ford Focus','Compact',20,'FORD-FOCUS-COMPACT-2');
insert into
car values(5,'Chevrolet Malibu','Full',30,'CHEVROLET-MALIBU-FULL-1');
insert into
car values(6,'Chevrolet Malibu','Full',30,'CHEVROLET-MALIBU-FULL-2');
insert into
car values(7,'Volkswagen Jetta','Full',30,'VOLKSWAGEN-JETTA-FULL-1');
insert into
car values(8,'Volkswagen Jetta','Full',30,'VOLKSWAGEN-JETTA-FULL-3');
insert into
car values(9,'Ford Egde','Large',40,'FORD-EDGE-LARGE-1');
insert into
car values(10,'Ford Egde','Large',40,'FORD-EDGE-LARGE-2');
insert into
car values(11,'Ford Escape','Large',40,'FORD-ESCAPE-LARGE-1');
insert into
car values(12,'Ford Escape','Large',40,'FORD-ESCAPE-LARGE-2');
insert into
car values(13,'BMW 328i','Luxury',40,'BMW-328i-LUXURY-1');
insert into
car values(14,'BMW 328i','Luxury',40,'BMW-328i-LUXURY-2');
insert into
car values(15,'BMW X5','Luxury',40,'BMW-X5-LUXURY-1');
insert into
car values(16,'BMW X5','Luxury',40,'BMW-X5-LUXURY-1');

commit;

select * from car;