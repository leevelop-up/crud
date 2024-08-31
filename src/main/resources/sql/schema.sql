create table member
(
    member_no    bigint auto_increment comment '회원 번호 (고유 식별자)'
        primary key,
    member_name  varchar(12)     not null comment '회원 이름 (최대 한글 4글자)',
    phone_number varchar(15)     not null comment '전화번호',
    gender       enum ('m', 'f') not null comment '성별 (m: 남성, f: 여성)',
    birth        date            not null comment '생년월일',
    email        varchar(100)    not null comment '이메일 주소',
    role         varchar(20)     not null comment '역할 (예: 사용자, 관리자)'
);

create table shop
(
    shop_id       bigint auto_increment comment '가게 ID (고유 식별자)'
        primary key,
    shop_name     varchar(100)  not null comment '가게 이름',
    rating        decimal(3, 1) not null comment '가게 평점',
    category      varchar(50)   not null comment '가게 카테고리',
    city          varchar(50)   not null comment '도시',
    district      varchar(50)   not null comment '구/군',
    capacity      int           not null comment '수용 가능 인원',
    waiting_count int           not null comment '대기자 수',
    opening_time  time          not null comment '영업 시작 시간',
    closing_time  time          not null comment '영업 종료 시간'
);

create table menu
(
    menu_id     bigint auto_increment comment '메뉴 ID (고유 식별자)'
        primary key,
    shop_id     bigint       not null comment '가게 ID (참조: shop.shop_id)',
    menu_name   varchar(100) not null comment '메뉴 이름',
    price       int          not null comment '가격',
    description text         null comment '메뉴 설명',
    constraint menu_ibfk_1
        foreign key (shop_id) references shop (shop_id)
);

create index shop_id
    on menu (shop_id);

create table owner
(
    owner_id     bigint auto_increment comment '점주 ID (고유 식별자)'
        primary key,
    shop_id      bigint          not null comment '가게 ID (참조: shop.shop_id)',
    owner_name   varchar(12)     not null comment '점주 이름 (최대 한글 4글자)',
    email        varchar(100)    not null comment '이메일 주소',
    password     varchar(100)    not null comment '비밀번호',
    phone_number varchar(15)     not null comment '전화번호',
    date_birth   date            not null comment '생년월일',
    constraint owner_ibfk_1
        foreign key (shop_id) references shop (shop_id)
);

create index shop_id
    on owner (shop_id);

create table reservation_time
(
    reservation_time_id bigint auto_increment comment '예약 시간 ID (고유 식별자)'
        primary key,
    shop_id             bigint   not null comment '가게 ID (참조: shop.shop_id)',
    time                datetime not null comment '예약 시간',
    is_occupied         tinyint  not null comment '예약 여부 (0: 예약 가능, 1: 예약 불가)',
    is_pre_occupied     tinyint  null comment '예약 선점 여부 (0: 미선점, 1: 선점)',
    constraint reservation_time_ibfk_1
        foreign key (shop_id) references shop (shop_id)
);

create table reservation
(
    reservation_id      bigint auto_increment comment '예약 ID (고유 식별자)'
        primary key,
    member_no           bigint      not null comment '회원 번호 (참조: member.member_no)',
    shop_id             bigint      not null comment '가게 ID (참조: shop.shop_id)',
    reservation_time_id bigint      not null comment '예약 시간 ID (참조: reservation_time.reservation_time_id)',
    status              varchar(20) not null comment '예약 상태',
    people_count        int         not null comment '예약 인원 수',
    constraint reservation_ibfk_1
        foreign key (member_no) references member (member_no),
    constraint reservation_ibfk_2
        foreign key (shop_id) references shop (shop_id),
    constraint reservation_ibfk_3
        foreign key (reservation_time_id) references reservation_time (reservation_time_id)
);

create index member_no
    on reservation (member_no);

create index reservation_time_id
    on reservation (reservation_time_id);

create index shop_id
    on reservation (shop_id);

create index shop_id
    on reservation_time (shop_id);

create table waiting
(
    waiting_id               bigint auto_increment comment '대기 ID (고유 식별자)'
        primary key,
    member_no                bigint      not null comment '회원 번호 (참조: member.member_no)',
    shop_id                  bigint      not null comment '가게 ID (참조: shop.shop_id)',
    waiting_number           int         not null comment '대기 번호',
    people_count             int         not null comment '대기 인원 수',
    status                   varchar(20) not null comment '대기 상태',
    remaining_postpone_count int         not null comment '남은 미루기 횟수',
    constraint waiting_ibfk_1
        foreign key (member_no) references member (member_no),
    constraint waiting_ibfk_2
        foreign key (shop_id) references shop (shop_id)
);

create index member_no
    on waiting (member_no);

create index shop_id
    on waiting (shop_id);

