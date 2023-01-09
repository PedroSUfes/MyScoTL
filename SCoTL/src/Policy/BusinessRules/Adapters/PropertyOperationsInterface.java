package Policy.BusinessRules;

import Policy.Entity.Property;

public interface PropertyOperationsInterface 
{
    public Property[] GetProperties();
    public Property GetPropertyById(String id);
    public Boolean TryRegisterProperty(Property property);
    public Boolean TryUpdateProperty(Property property);
    public Boolean TryRemoveProperty(String id);
}
