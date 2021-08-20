# GatwaysRepo

## Description
This sample project is managing gateways - master devices that control multiple peripheral
devices.

Your task is to create a REST service (JSON/HTTP) for storing information about these
gateways and their associated devices. This information must be stored in the database.
When storing a gateway, any field marked as “to be validated” must be validated and an
error returned if it is invalid. Also, no more that 10 peripheral devices are allowed for a
gateway.

The service must also offer an operation for displaying information about all stored gateways
(and their devices) and an operation for displaying details for a single gateway. Finally, it
must be possible to add and remove a device from a gateway.

### Each gateway has:
- a unique serial number (string)
- human-readable name (string),
- IPv4 address (to be validated),
- multiple associated peripheral devices.

### Each peripheral device has:
- a UID (number),
- vendor (string),
- date created,
- status - online/offline.

### Database:

use mysql database and include db scripts in scripts folder , also include db connection :

url=jdbc:mysql://mysql-45715-0.cloudclusters.net:18848/gatewayDB
username=admin
password=6VjNsuMU

### Deployment:

use wildfly server and make jndi name : sql jndi to refer to connections 
in wildfly server for datasource using the same credentials in database 
section , also I attach deployment war that can easily deployed on server
in deployment folder.

### Endpoints:


Endpoints code appear in source code folder and all of endpoints displayed in postman collections folder with test examples

and response and request , also every single entry point have screenshot in screenshots folder.

## Devices Endpoints:
## Create Device Endpoint :
POST http://localhost:8080/Gateways-API/devices/{gatewayId}/createdevice
body:{
    "uid" : 1251992,
    "vendor" : "vendor13",
    "status" : "online"
}

![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/create_device_1.png?raw=true "Image 1")
![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/create_device_with_repeated_uid_2.png?raw=true "Image 2)
![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/create_device_with_wrong_status_3.png?raw=true "Image 3)

## Get All Devices Endpoint:
GET http://localhost:8080/Gateways-API/devices/{gatewayId}/getdevices

![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/get_all_devices_1.png?raw=true "Image 4")

## Update Device Endpoint:

POST http://localhost:8080/Gateways-API/devices/{gatewayId}/updatedevice/{deviceId}
body{
    "vendor": "vendor_testing",
    "status": "online"
}

![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/update_Device.png?raw=true "Image 5")


## Delete Device Endpoint:

DELETE  http://localhost:8080/Gateways-API/devices/{gatewayId}/deletedevice/{deviceId}

![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/delete_device.png?raw=true "Image 6")

### Gateways Endpoints:
## Create Gateway:

POST  http://localhost:8080/Gateways-API/gateways/create
body{
    "serialNumber" : "23244",
    "name" : "rawan",
    "ipv4Address" : "152.109.212"
}

![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/create_gateway_1.png?raw=true "Image 7")
![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/create_gateway_with_repeated_serial_3.png?raw=true "Image 8")
![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/create_gateway_with_wrong_ip_2.png?raw=true "Image 9")


## Get All Gateways:

GET  http://localhost:8080/Gateways-API/gateways/getall

![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/get_all_gateways_1.png?raw=true "Image 10")

## Update Gateway :
POST http://localhost:8080/Gateways-API/gateways/update/{gatewayId}
body{
    "serialNumber": "2021"
}

![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/update_gateway.png?raw=true "Image 11")
![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/update_gateway_gateway_not_found.png?raw=true "Image 12")

## Delete Gateway :
DELETE http://localhost:8080/Gateways-API/gateways/delete/{gatewayId}

![Alt text](request_screenshots_from_postman/request_screenshots_from_postman/delete_gateway.png?raw=true "Image 13")







