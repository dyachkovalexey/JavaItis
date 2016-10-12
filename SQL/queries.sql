SELECT * FROM car_owner WHERE owner_city = 'Казань';
SELECT * FROM car_owner WHERE owner_id IN (SELECT owner_id FROM auto_car_owner WHERE auto_id IN (SELECT auto_id FROM auto WHERE mileage > 100));
SELECT * FROM car_owner, auto, auto_car_owner WHERE (car_owner.owner_age > 20) AND (auto.mileage = 50) AND (auto_car_owner.auto_id = auto.auto_id) AND (auto_car_owner.owner_id = car_owner.owner_id);
