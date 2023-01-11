package Policy.BusinessRules;

import Policy.Entity.Property;

public class CRUDProperty 
{
    public static Property[] GetProperties()
    {
        return DatabaseAccess.propertyOperationsInterface.GetProperties();
    }

    public static Property GetPropertyById(String id)
    {
        return DatabaseAccess.propertyOperationsInterface.GetPropertyById(id);
    }

    public static Boolean TryRegisterProperty(Property property)
    {
        return DatabaseAccess.propertyOperationsInterface.TryRegisterProperty(property);
    }

    public static Boolean TryUpdateProperty(Property property)
    {
        return DatabaseAccess.propertyOperationsInterface.TryRegisterProperty(property);
    }
    
    public static Boolean TryRemoveProperty(String id)
    {
        return DatabaseAccess.propertyOperationsInterface.TryRemoveProperty(id);
    }
}
