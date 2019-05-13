// nino (vehicle using a distinctive sign) agent

/* Initial beliefs */


/* Initial goal */

!start.

/* Plans */

+!start :true<-
	.wait(25000);
	.send(car1,tell,ninocoming);
	.send(car2,tell,ninocoming);
	.send(car3,tell,ninocoming);
	.send(car4,tell,ninocoming);
	.send(pedestrian, tell, ninocoming);

	+domove;
	!move.
	
+!move :domove <- 
	move(nino);
	!move.

+!move: not domove<-
	!restart;
	arrived.

+ninoarrived: true<-
	.send(car1,untell,ninocoming);
	.send(car2,untell,ninocoming);
	.send(car3,untell,ninocoming);
	.send(car4,untell,ninocoming);
	.send(pedestrian, untell, ninocoming);

	.send(car1,tell,ninoarrived);
	.send(car2,tell,ninoarrived);
	.send(car3,tell,ninoarrived);
	.send(car4,tell,ninoarrived);
	.send(pedestrian, tell, ninoarrived);

	-domove.	

+!restart :true<-
	.wait(45000);
	.send(car1,tell,ninocoming);
	.send(car2,tell,ninocoming);
	.send(car3,tell,ninocoming);
	.send(car4,tell,ninocoming);
	.send(pedestrian, tell, ninocoming);

	.send(car1,untell,ninoarrived);
	.send(car2,untell,ninoarrived);
	.send(car3,untell,ninoarrived);
	.send(car4,untell,ninoarrived);
	.send(pedestrian, untell, ninoarrived);

	+domove;
	!move.