insert into pharmacy (address, head, district, phone)
values ('street 1', 'head1', 'district1', '+1111111111'),
       ('street 2', 'head2', 'district2', '+2222222222');

insert into drug (name, category, price, conditions)
values ('drug1', 'category1', 100, 'condition1'),
       ('drug2', 'category2', 200, 'condition2');


insert into link_pharmacy_drug (pharmacy_ref, drug_ref, quantity)
values (1, 2, 20),
       (2, 1, 10);

