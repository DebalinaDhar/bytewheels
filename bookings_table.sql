use bytewheels;

CREATE table bookings(
booking_id int not null,
serial_number varchar(45) not null,
model_name varchar(45) not null,
from_date date not null,
to_date date not null,
status varchar(45) not null,
email varchar(45) not null,
total_cost int not null,
no_of_days int not null,
primary key(booking_id2)
);

commit;

select * from bookings;

desc bookings;