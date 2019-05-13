// car1 agent

/* Initial beliefs */


/* Initial goal */

!move. 

/* Plans */

+!move : ninocoming<-
	wait(car1).

+!move : true <-
	move(car1);
	!move.

+ninoarrived:true<-
	!move.
