// lamp2 agent

/* Initial beliefs */


/* Initial goal */

!start.

/* Plans */

+!start: true<-
	!turnoff.

	  
+!turnoff : true <- 
	turnoff2;
	.wait(25000);
	!green.
	
+!green : true <- 
	.wait(10000);
	switch2;
	  !red.

+!red : true <- 
	.wait(10000);
	switch2;
	  !green.
