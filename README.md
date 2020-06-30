# Distributed-Room-Reservation-System-using-Java-RMI

## Client Server Architecture Distributed Room Reservation System (DRRS) using Java RMI

The Distributed Room Reservation System is a distributed system that allows the Administrators to manage rooms by creating and deleting according to their availability. It allows the Students to manage their bookings by letting them book, cancel and check available rooms.

The system uses **Java RMI** to facilitate the communication between remote objects. It also uses **UDP connection** to communicate and get available timeslots from peer servers.

## Java RMI

The RMI ( Remote Method Invocation ) is an API  that provides a mechanism to create distributed applications In java. The RMI mechanism works by allowing an object to invoke a method on an object running in another JVM.


## 	ARCHITECTURE 
There are 3 campuses namely Dorval-Campus ( DVL), Kirkland-Campus (KKL) and Westmount-Campus (WST). Each Campus is assigned a Server. The Clients i.e. the Students and the Admins have varying accessibility. The Admins can either Create a room or Delete a room whereas the Students can Book a room, get available Time-Slots and  Cancel the booking.

The Student and the Admin are represented by respective classes. These classes can call remote methods present on the server through  JAVA RMI. Each student is associated with a particular campus which is known by the User Id. To get available Time-Slots present on other servers, the server associated with the student has to communicate with them using UDP Connection.

![](Architecture.jpg)

## 
