class Task {
private String id;
private String description;
private int priority;
 
Task(String id,String description,int priority)
{
this.id = id;
this.description=description;
this.priority=priority;
}

public String getId()
{
    return id;
}

public String getDescription()
{
    return description;
}

public int getPriority()
{
    return priority;    
}

@Override
public String toString()
{
    return String.format("Task[id=%s, description=%s, priority=%d]", id, description, priority);
}   
    
}