-- -----------------------------------------------------
-- Schema cabindb
-- -----------------------------------------------------

IF DB_ID('cabindb') IS NOT NULL
   --code mine :)
   PRINT 'cabindb exists'
ELSE
    CREATE DATABASE [cabindb];
    GO

USE [cabindb];
GO

-- -----------------------------------------------------
-- Table `cabindb`.`post`
-- -----------------------------------------------------


IF object_id('post', 'U') is not null
    PRINT 'Table post already exists!'
ELSE
    CREATE TABLE post (
        zip CHAR(5) NOT NULL,
        city VARCHAR(45) NULL,
        PRIMARY KEY (zip)
    );
    GO

-- -----------------------------------------------------
-- Table `cabindb`.`customer`
-- -----------------------------------------------------

IF object_id('customer', 'U') is not null
    PRINT 'Table customer already exists!'
ELSE
    CREATE TABLE customer (
      customer_id INT NOT NULL IDENTITY(1,1),
      zip CHAR(5) NOT NULL,
      firstname VARCHAR(20) NULL DEFAULT NULL,
      lastname VARCHAR(40) NULL DEFAULT NULL,
      address VARCHAR(40) NULL DEFAULT NULL,
      email VARCHAR(50) NULL DEFAULT NULL,
      phone VARCHAR(15) NULL DEFAULT NULL,
      PRIMARY KEY (customer_id),
      INDEX fk_as_post_idx (zip ASC),
      INDEX customer_lname_idx (lastname ASC),
      INDEX customer_fname_idx (firstname ASC),
      CONSTRAINT fk_customer_post
        FOREIGN KEY (zip)
        REFERENCES [post] (zip)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
    GO
-- -----------------------------------------------------
-- Table `cabindb`.`location`
-- -----------------------------------------------------

IF object_id('location', 'U') is not null
    PRINT 'Table location already exists!'
ELSE
    CREATE TABLE [location] (
      location_id INT NOT NULL IDENTITY(1,1),
      name VARCHAR(40) NULL DEFAULT NULL,
      PRIMARY KEY (location_id),
      INDEX location_name_index (name ASC));
    GO

-- -----------------------------------------------------
-- Table `cabindb`.`cabin`
-- -----------------------------------------------------

IF object_id('cabin', 'U') is not null
    PRINT 'Table cabin already exists!'
ELSE
    CREATE TABLE [cabin] (
      cabin_id int NOT NULL IDENTITY(1,1),
      location_id int NOT NULL,
      zip CHAR(5) NOT NULL,
      cabinname VARCHAR(45) NULL,
      cabinaddress VARCHAR(45) NULL,
      price money NOT NULL,
      description VARCHAR(150) NULL,
      capacity int NULL,
      amenities VARCHAR(100) NULL,
      PRIMARY KEY (cabin_id),
      INDEX fk_cabin_location_idx (location_id ASC),
      INDEX fk_cabin_post_idx (zip ASC),
      CONSTRAINT fk_cabin_location
        FOREIGN KEY (location_id)
        REFERENCES [location] (location_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT fk_cabin_post
        FOREIGN KEY (zip)
        REFERENCES [post] (zip)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);
    GO

-- -----------------------------------------------------
-- Table `cabindb`.`reservation`
-- -----------------------------------------------------

IF object_id('reservation', 'U') is not null
    PRINT 'Table reservation already exists!'
ELSE
    CREATE TABLE [reservation] (
      reservation_id INT NOT NULL IDENTITY(1,1),
      customer_id INT NOT NULL,
      cabin_id INT NOT NULL,
      reserved_date DATETIME NULL DEFAULT NULL,
      confirmation_date DATETIME NULL DEFAULT NULL,
      reservation_start DATETIME NULL DEFAULT NULL,
      reservation_end DATETIME NULL DEFAULT NULL,
      PRIMARY KEY (reservation_id),
      INDEX reservation_customer_id_index (customer_id ASC),
      INDEX fk_reservation_cabin_idx (cabin_id ASC),
      CONSTRAINT fk_reservation_customer
        FOREIGN KEY (customer_id)
        REFERENCES [customer] (customer_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
      CONSTRAINT fk_reservation_cabin
        FOREIGN KEY (cabin_id)
        REFERENCES [cabin] (cabin_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);
    GO

-- -----------------------------------------------------
-- Table `cabindb`.`invoice`
-- -----------------------------------------------------

IF object_id('invoice', 'U') is not null
    PRINT 'Table invoice already exists!'
ELSE
    CREATE TABLE [invoice] (
      invoice_id int NOT NULL IDENTITY,
      reservation_id int NOT NULL,
      total money NOT NULL,
      vat money NOT NULL,
      PRIMARY KEY (invoice_id),
      CONSTRAINT fk_invoice_reservation
        FOREIGN KEY (reservation_id)
        REFERENCES [reservation] (reservation_id)
        ON DELETE NO ACTION);
    GO

-- -----------------------------------------------------
-- Table `cabindb`.`service`
-- -----------------------------------------------------

IF object_id('service', 'U') is not null
    PRINT 'Table service already exists!'
ELSE
    CREATE TABLE [service] (
      service_id int NOT NULL IDENTITY(1,1),
      location_id int NOT NULL,
      name VARCHAR(40) NULL DEFAULT NULL,
      type int NULL DEFAULT NULL,
      description VARCHAR(255) NULL DEFAULT NULL,
      price money NOT NULL,
      vat money NOT NULL,
      PRIMARY KEY (service_id),
      INDEX service_name_index (name ASC),
      INDEX service_location_index (location_id ASC),
      CONSTRAINT fk_service_location
        FOREIGN KEY (location_id)
        REFERENCES [location] (location_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION);
    GO

-- -----------------------------------------------------
-- Table `cabindb`.`reservation_services`
-- -----------------------------------------------------

IF object_id('reservation_services', 'U') is not null
    PRINT 'Table reservation_services already exists!'
ELSE
    CREATE TABLE [reservation_services] (
      reservation_id int NOT NULL,
      service_id int NOT NULL,
      number int NOT NULL,
      INDEX rs_reservation_id_index (reservation_id ASC),
      INDEX rs_service_id_index (service_id ASC),
      PRIMARY KEY (service_id, reservation_id),
      CONSTRAINT fk_reservation
        FOREIGN KEY (reservation_id)
        REFERENCES [reservation] (reservation_id)
        ON DELETE NO ACTION,
      CONSTRAINT fk_service
        FOREIGN KEY (service_id)
        REFERENCES [service] (service_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION)
