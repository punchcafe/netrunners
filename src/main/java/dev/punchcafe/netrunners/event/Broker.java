package dev.punchcafe.netrunners.event;

public class Broker {
    //Add a message queue, and make sure everyone gets their message
    // Have two types, blocking and non blocking. Blocking events may add to the queue, but nothing else
    // can be consumed until afterwards

    // Alternatively, can have priority?
    // Adittionally, have reactional / observer subscribers, to separate those which need to.
    // Or, have a blocking animation/render enginebut the issue there is each Event needs to know when to render
    // lastly, you could always have a core Actor decorator class which adds something to ever method call.
}
