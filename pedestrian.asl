// pedestrian agent

/* Initial beliefs */


/* Initial goal */

!move.

/* Plans */

+!move : ninocoming<-
	wait(pedestrian).

+!move : true <-
	.wait(4000)
	move(pedestrian);
	!move.

+ninoarrived:true<-
	!move.
