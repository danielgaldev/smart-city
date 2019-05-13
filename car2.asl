// car2 agent

/* Initial beliefs */


/* Initial goal */

!move. 

/* Plans */

+!move : ninocoming<-
	wait(car2).

+!move : true <-
	.wait(1000)
	move(car2);
	!move.

+ninoarrived:true<-
	!move.
