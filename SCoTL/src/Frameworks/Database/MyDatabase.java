package Frameworks.Database;

import java.util.ArrayList;
import java.util.List;

import Policy.BusinessRules.BatchOperationsInterface;
import Policy.BusinessRules.CoffeeBagOperationsInterface;
import Policy.BusinessRules.EmployeeOperationsInterface;
import Policy.BusinessRules.LoginInterface;
import Policy.BusinessRules.LoginManager;
import Policy.BusinessRules.PropertyOperationsInterface;
import Policy.BusinessRules.WarehouseOperationsInterface;
import Policy.Entity.Batch;
import Policy.Entity.CoffeeBag;
import Policy.Entity.Employee;
import Policy.Entity.Servant;
import Policy.Entity.Person;
import Policy.Entity.Property;
import Policy.Entity.User;
import Policy.Entity.Warehouse;
import Policy.Entity.WarehouseManager;
import Utility.ArrayHelper;
import Utility.ListHelper;

public class MyDatabase 
implements 
    BatchOperationsInterface, 
    CoffeeBagOperationsInterface,
    EmployeeOperationsInterface,
    LoginInterface,
    WarehouseOperationsInterface,
    PropertyOperationsInterface
{
    private class ManageWarehouse
    {
        private String m_warehouseId = null;
        private String m_managerCpf = null;
        private String m_beginDate = null;
        private String m_endDate = null;

        public ManageWarehouse
        (
            String warehouseId,
            String managerCpf,
            String beginDate
        )
        {
            m_warehouseId = warehouseId;
            m_managerCpf = managerCpf;
            m_beginDate = beginDate;
        }

        public String GetWarehouseId()
        {
            return m_warehouseId;
        }

        public String GetManagerCpf()
        {
            return m_managerCpf;
        }

        public String GetBeginDate()
        {
            return m_beginDate;
        }

        public String GetEndDate()
        {
            return m_endDate;
        }

        public void SetEndDate(String endDate)
        {
            m_endDate = endDate;
        }
    }

    private class IsWarehouseOwner
    {
        private String m_warehouseId = null;
        private String m_ownerCpf = null;
        private String m_beginDate = null;
        private String m_endDate = null;

        public IsWarehouseOwner
        (
            String warehouseId,
            String ownerCpf,
            String beginDate
        )
        {
            m_warehouseId = warehouseId;
            m_ownerCpf = ownerCpf;
            m_beginDate = beginDate;
        }

        public String GetWarehouseId()
        {
            return m_warehouseId;
        }

        public String GetOwnerCpf()
        {
            return m_ownerCpf;
        }

        public String GetBeginDate()
        {
            return m_beginDate;
        }

        public String GetEndDate()
        {
            return m_endDate;
        }

        public void SetEndDate(String endDate)
        {
            m_endDate = endDate;
        }
    }

    private class WorksOn
    {
        private String m_cpf = null;
        private String m_propertyId = null;
        private String m_beginDate = null;
        private String m_endDate = null;

        public WorksOn
        (
            String cpf,
            String propertyId,
            String beginDate
        )
        {
            m_cpf = cpf;
            m_propertyId = propertyId;
            m_beginDate = beginDate;
        }

        public String GetCpf()
        {
            return m_cpf;
        }

        public String GetPropertyId()
        {
            return m_propertyId;
        }

        public String GetBeginDate()
        {
            return m_beginDate;
        }

        public String GetEndDate()
        {
            return m_endDate;
        }

        public void SetEndDate(String endDate)
        {
            m_endDate = endDate;
        }
    }

    private class BuyBag
    {
        private String m_batchId = null;
        private String m_bagId = null;
        private String m_cpf = null;
        private String m_buyDate = null;

        public BuyBag
        (
            String batchId,
            String bagId, 
            String cpf,
            String buyDate
        )
        {
            m_batchId = batchId;
            m_bagId = bagId;
            m_cpf = cpf;
            m_buyDate = buyDate;
        }

        public String GetBatchId()
        {
            return m_batchId;
        }

        public String GetBagId()
        {
            return m_bagId;
        }

        public String GetCpf()
        {
            return m_cpf;
        }
    }

    private List<User> userList = new ArrayList<User>();
    private List<Person> personList = new ArrayList<Person>();
    private List<Warehouse> warehouseList = new ArrayList<Warehouse>();
    private List<ManageWarehouse> manageWarehouseList = new ArrayList<ManageWarehouse>();
    private List<IsWarehouseOwner> isWarehouseOwnerList = new ArrayList<IsWarehouseOwner>();
    private List<WorksOn> worksOnList = new ArrayList<WorksOn>();
    private List<BuyBag> buyBagList = new ArrayList<BuyBag>();
    private List<Property> propertyList = new ArrayList<Property>();

    public void AddUser(User user)
    {
        userList.add(user);
    }

    @Override
    public Boolean TryLogin(String login, String password) 
    {
        var iterator = userList.iterator();
        while(iterator.hasNext())
        {
            User user = iterator.next();
            if(user.GetLogin() == login && user.GetPassword() == password)
            {
                LoginManager.SetUserType(user.GetUserType());
                return true;
            }
        }

        System.out.println("Wrong login or password");
        return false;
    }

    @Override
    public Batch[] GetBatches() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Batch GetBatch(String batchId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean TryRegisterBatch(Batch batch) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean TryRemoveBatch(String batchId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CoffeeBag[] GetCoffeeBags() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CoffeeBag[] GetCoffeeBags(String batchId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CoffeeBag[] GetCoffeeBag(String batchId, String coffeeBagId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean TryRegisterCoffeeBag(CoffeeBag coffeeBag) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean TryRemoveBag(String batchId, String coffeeBagId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee[] GetEmployees() 
    {
        // Os funcionários são as pessoas cujos cpf's são "mencionados" nas tabelas worksOn e ManageWarehouse
        // Valores de chaves repedidos devem ser descartados.
        var cpfList = new ArrayList<String>();
        var stringListHelper = new ListHelper<String>();

        for(var element : worksOnList)
        {
            if(element == null)
            {
                continue;
            }

            if(stringListHelper.Exists(cpfList, (e) -> e == element.GetCpf()))
            {
                continue;
            }

            cpfList.add(element.GetCpf());
        }

        for(var element : manageWarehouseList)
        {
            if(element == null)
            {
                continue;
            }

            if(stringListHelper.Exists(cpfList, (e) -> e == element.GetManagerCpf()))
            {
                continue;
            }

            cpfList.add(element.GetManagerCpf());
        }

        var toReturn = new Employee[cpfList.size()];
        int currentIndex = 0;
        var personListHelper = new ListHelper<Person>();
        for(var cpf : cpfList)
        {
            if(cpf == null)
            {
                continue;
            }

            var employee = (Employee) personListHelper.Find(personList, (e) -> e.GetCpf() == cpf);
            toReturn[currentIndex] = Employee.GetEmployeeCopy(employee);
            ++currentIndex;
        }

        return toReturn;
    }

    @Override
    public Employee GetEmployee(String cpf) 
    {
        // Os empregados têm suas chaves primárias presentes nas tabelas worksOn e ManageWarehouse
        // Deve-se usar essas tabelas para resolver para a classe Employee

        var worksOnListHelper = new ListHelper<WorksOn>();
        var manageWarehouseListHelper = new ListHelper<ManageWarehouse>();

        if
        (
            worksOnListHelper.Exists(worksOnList, (e) -> e.GetCpf() == cpf) || 
            manageWarehouseListHelper.Exists(manageWarehouseList, (e) -> e.GetManagerCpf() == cpf)
        )
        {
            var personListHelper = new ListHelper<Person>();
            var employee = (Employee) personListHelper.Find(personList, (e) -> e.GetCpf() == cpf);
            return Employee.GetEmployeeCopy(employee);
        }
        
        System.out.println("A employee with cpf "+cpf+" is not in register");
        System.out.println("Fail to update");
        
        return null;
    }

    @Override
    public Boolean TryRegisterServant(Servant servant, String beginDate) 
    {
        // Verificar se a propriedade está cadastrada
            // Se não estiver, uma mensagem de erro deve ser exibida e a função retorna falso
        // Verificar se o servente está cadastrado
        // Se não estiver, deve o cadastrar
        // Verificar a tabela worksOn. Caso já tenha uma tupla equivalente aos parâmetros dessa função,
        // se a data de fim estiver setada, deve-se cadastrar o novo registro.

        var propertyListHelper = new ListHelper<Property>();
        if(!propertyListHelper.Exists(propertyList, (e) -> e.GetId() == servant.GetProperty().GetId()))
        {
            System.out.println("Property with id"+servant.GetProperty().GetId()+" not in register");
            return false;
        }

        var personListHelper = new ListHelper<Person>();
        if(!personListHelper.Exists(personList, (e) -> e.GetCpf() == servant.GetCpf()))
        {
            personList.add(servant);
        }

        var worksOnListHelper = new ListHelper<WorksOn>();
        var worksOnTuple = worksOnListHelper.Find
        (
            worksOnList, (e) -> e.GetCpf() == servant.GetCpf() && e.GetPropertyId() == servant.GetProperty().GetId()
        );

        if(worksOnTuple == null)
        {
            worksOnList.add(new WorksOn(servant.GetCpf(), servant.GetProperty().GetId(), beginDate));
            return true;
        }
        
        if(worksOnTuple.GetEndDate() == null)
        {
            System.out.println
            (
                "Servant with cpf "+servant.GetCpf()+" already works on property with id "+servant.GetProperty().GetId()+"."
            );

            return false;
        }
        
        worksOnList.add(new WorksOn(servant.GetCpf(), servant.GetProperty().GetId(), beginDate));
        return true;
    }

    @Override
    public Boolean TryRegisterWarehouseManager(WarehouseManager warehouseManager, String beginDate) 
    {
        // Caso o galpão não exista, deve ser exibida uma mensagem de erro e retornar falso
        // Caso o gerente não esteja cadastrado na tabela pessoa, deve-se o cadastrar
        // Caso o gerente já admistre o galpão, deve-se exibir uma mensagem de erro e retornar falso
        // Caso algum outro gerente já administre o galpão, deve-se exibir uma mensagem de erro e retornar falso

        var warehouseListHelper = new ListHelper<Warehouse>();
        if(!warehouseListHelper.Exists(warehouseList, (e) -> e.GetId() == warehouseManager.GetWarehouse().GetId()))
        {
            System.out.println("Warehouse with id "+warehouseManager.GetWarehouse().GetId()+" not in register");
            return false;
        }

        var personListHelper = new ListHelper<Person>();
        if(!personListHelper.Exists(personList, (e) -> e.GetCpf() == warehouseManager.GetCpf()))
        {
            personList.add(warehouseManager);
        }

        var manageWarehouseListHelper = new ListHelper<ManageWarehouse>();

        var manageWarehousesTuples = manageWarehouseListHelper.FindAllThat
        (
            manageWarehouseList, 
            (e) -> e.GetWarehouseId() == warehouseManager.GetWarehouse().GetId() && 
            e.GetManagerCpf() != warehouseManager.GetCpf()
        );
        if(manageWarehousesTuples.size() > 0)
        {
            for(var element : manageWarehousesTuples)
            {
                if(element == null)
                {
                    continue;
                }

                if(element.GetEndDate() == null)
                {
                    System.out.println("Warehouse already under management of warehouser manager with cpf "+element.GetManagerCpf());
                    System.out.println("Fail to register warehouser manager with cpf "+warehouseManager.GetCpf());
                    return false;
                }
            }
        }

        var manageWarehouseTuple = manageWarehouseListHelper.Find
        (
            manageWarehouseList, 
            (e) -> e.GetManagerCpf() == warehouseManager.GetCpf() && 
            e.GetWarehouseId() == warehouseManager.GetWarehouse().GetId()
        );

        if(manageWarehouseTuple == null)
        {
            manageWarehouseList.add(new ManageWarehouse
            (
                warehouseManager.GetWarehouse().GetId(), 
                warehouseManager.GetCpf(),
                beginDate
            ));

            return true;
        }
        
        if(manageWarehouseTuple.GetEndDate() == null)
        {
            System.out.println
            (
                "Warehouse manager with cpf "+warehouseManager.GetCpf()+" already manage warehouse with id "+warehouseManager.GetWarehouse().GetId()
            );
            System.out.println("Fail to register warehouser manager with cpf "+warehouseManager.GetCpf());
            return false;
        }
        
        manageWarehouseList.add(new ManageWarehouse
        (
            warehouseManager.GetCpf(), warehouseManager.GetWarehouse().GetId(), beginDate
        ));

        return true;
    }

    @Override
    public Boolean TryUpdateServant(Servant servant) 
    {
        var worksOnListHelper = new ListHelper<WorksOn>();
        if(!worksOnListHelper.Exists(worksOnList, (e) -> e.GetCpf() == servant.GetCpf()))
        {
            System.out.println("Servant with cpf "+servant.GetCpf()+" not in register.");
            System.out.println("Fail to update");
            return false;
        }

        var personListHelper = new ListHelper<Person>();
        var inDatabaseServant = (Servant) personListHelper.Find(personList, (e) -> e.GetCpf() == servant.GetCpf());

        if(inDatabaseServant.GetProperty() != servant.GetProperty())
        {
            System.out.println("Invalid update operation. Use TryUpdateServantProperty to change the property");
            System.out.println("Fail to update servant with cpf "+servant.GetCpf());
            return false;
        }

        personListHelper.ReplaceThat(personList, (e) -> e.GetCpf() == servant.GetCpf(), servant);
        return true;
    }

    @Override
    public Boolean TryUpdateServantProperty(String servantCpf, Property property, String date)
    {
        // Deve-se verificar se o cpf passado é de um servente. Se não for, deve-se exibir uma mensagem de erro e retornar falso
        // Deve-se verificar se a propriedade está cadastrada. Se não estiver deve-se exibir uma mensagem de erro e retornar falso
        // Deve-se verificar se o servente já não trabalha nessa propriedade. Se já trabalhar uma mensagem de erro e retornar falso
        // Lembrar de setar as datas de fim e de inicio

        var worksOnListHelper = new ListHelper<WorksOn>();
        if(!worksOnListHelper.Exists(worksOnList, (e) -> e.GetCpf() == servantCpf))
        {
            System.out.println("The informed cpf is not of a servant. Fail to update servant with cpf "+servantCpf);
            return false;
        }
        
        var propertyListHelper = new ListHelper<Property>();
        if(!propertyListHelper.Exists(propertyList, (e) -> e.GetId() == property.GetId()))
        {
            System.out.println("The property with id "+property.GetId()+" is not in register. Fail to update servant with cpf "+servantCpf);
            return false;
        }

        var personListHelper = new ListHelper<Person>();
        var toUpdate = (Servant) personListHelper.Find(personList, (e) -> e.GetCpf() == servantCpf);
        
        if(toUpdate.GetProperty().GetId() == property.GetId())
        {
            System.out.println("The servant with cpf "+servantCpf+" alrady works on property with id "+property.GetId());
            return false;
        }

        // Antes de setar a propriedade, deve-se atualizar a tabela de relacionamento works on
        var worksOnOldTuple = worksOnListHelper.Find
        (
            worksOnList, 
            (e) -> e.GetCpf() == servantCpf && 
            e.GetEndDate() == null
        );

        worksOnOldTuple.SetEndDate(date);
        worksOnList.add(new WorksOn(servantCpf, property.GetId(), date));

        toUpdate.SetProperty(property);

        return true;
    }

    @Override
    public Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager) 
    {
        // Não se pode atualizar o galpão administrado nesse método. Caso se tente fazer isso,
        // deve-se imprimir uma mensagem e retornar falso
        // Caso o cpf informado não seja de um gerente ou não estja cadastrado,
        // deve-se imprimir uma mensagem e retornar falso

        var manageWarehouseListHelper = new ListHelper<ManageWarehouse>();
        if(!manageWarehouseListHelper.Exists(manageWarehouseList, (e) -> e.GetManagerCpf() == warehouseManager.GetCpf()))
        {
            System.out.println("Warehouse manager with cpf "+warehouseManager.GetCpf()+" not in register. Fail to update");
            return false;
        }

        var personListHelper = new ListHelper<Person>();
        var inDatabaseWarehouseManager = (WarehouseManager) personListHelper.Find
        (
            personList, 
            (e) -> e.GetCpf() == warehouseManager.GetCpf()
        ); 

        if(inDatabaseWarehouseManager.GetWarehouse() != warehouseManager.GetWarehouse())
        {
            System.out.println("Invalid update operation. Use TryUpdateWarehouseManagerWarehouse to change the warehouse");
            System.out.println("Fail to update warehouse manager with cpf "+warehouseManager.GetCpf());
            return false;
        }

        personListHelper.ReplaceThat(personList, (e) -> e.GetCpf() == warehouseManager.GetCpf(), warehouseManager);
        return true;
    }

    @Override
    public Boolean TryUpdateWarehouseManagerWarehouse(String warehouseManagerCpf, Warehouse warehouse, String date)
    {
        // Caso o cpf não seja de um gerente de galpão, deve-se imprimir uma mensagem de erro e retornar falso
        // Caso o galpão não estja cadastrado, deve-se imprimir uma mensagem de erro e retornar falso
        // Caso o gerente já gerencie o galpão, deve-se imprimir uma mensagem de erro e retornar falso
        // Caso algum outro gerente já gerencie o galpão, deve-se imprimir uma mensagem de erro e retornar falso
        // Lembrar de atualizar a tabela manageWarehouse

        var manageWarehouseListHelper = new ListHelper<ManageWarehouse>();
        if(!manageWarehouseListHelper.Exists(manageWarehouseList, (e) -> e.GetManagerCpf() == warehouseManagerCpf))
        {
            System.out.println("Warehouse manager with cpf "+warehouseManagerCpf+" is not in register");
            System.out.println("Fail to update");
            return false;
        }

        var warehouseListHelper = new ListHelper<Warehouse>();
        if(!warehouseListHelper.Exists(warehouseList, (e) -> e.GetId() == warehouse.GetId()))
        {
            System.out.println("Warehouse with id "+warehouse.GetId()+" is not in register");
            System.out.println("Fail to update");
            return false;
        }

        if(manageWarehouseListHelper.Exists
        (
            manageWarehouseList, (e) -> e.GetManagerCpf() == warehouseManagerCpf &&
            e.GetWarehouseId() == warehouse.GetId() &&
            e.GetEndDate() == null
        ))
        {
            System.out.println("Warehouse manager with cpf "+warehouseManagerCpf+" already manages warehouse with id "+warehouse.GetId());
            System.out.println("Fail to update");
            return false;
        }

        if(manageWarehouseListHelper.Exists
        (
            manageWarehouseList, 
            (e) -> e.GetWarehouseId() == warehouse.GetId() &&
            e.GetEndDate() == null
        ))
        {
            System.out.println("The warehouse with id "+warehouse.GetId()+" already have a manager");
            System.out.println("Fail to update");
            return false;
        }

        var oldManageWarehouseTuple = manageWarehouseListHelper.Find
        (
            manageWarehouseList, 
            (e) -> e.GetManagerCpf() == warehouseManagerCpf &&
            e.GetEndDate() == null
        );

        oldManageWarehouseTuple.SetEndDate(date);
        manageWarehouseList.add(new ManageWarehouse(warehouse.GetId(), warehouseManagerCpf, date));

        var personListHelper = new ListHelper<Person>();
        var warehouseManager = (WarehouseManager) personListHelper.Find(personList, (e) -> e.GetCpf() == warehouseManagerCpf);
        warehouseManager.SetWarehouse(warehouse);

        return true;
    }

    @Override
    public Boolean TryRemoveEmployee(String cpf) 
    {
        // Deve verificar se é realmente o cpf de um funcionario (existe nas tabelas worksOn e ManageWarehouse)
        // Deve-se remover o employee das tabelas de employees, mas só se deve o remover do sistema se nenhuma outra tabela o referenciar

        var worksOnListHelper = new ListHelper<WorksOn>();
        var managerWarehouseListHelper = new ListHelper<ManageWarehouse>();
        if
        (
            !worksOnListHelper.Exists(worksOnList, (e) -> e.GetCpf() == cpf) &&
            !managerWarehouseListHelper.Exists(manageWarehouseList, (e) -> e.GetManagerCpf() == cpf
        ))
        {
            System.out.println("The cpf "+cpf+" is not owned by a employee. Fail to remove.");
            return false;
        }

        worksOnList.removeIf((e) -> e.GetCpf() == cpf);
        manageWarehouseList.removeIf((e) -> e.GetManagerCpf() == cpf);
        
        if(GetNumberOfReferences(cpf) == 0)
        {
            personList.removeIf((e) -> e.GetCpf() == cpf);
        }

        return true;
    }

    @Override
    public Warehouse[] GetWarehouses() 
    {
        var toReturn = new Warehouse[warehouseList.size()];
        int currentIndex = 0;
        for(var element : warehouseList)
        {
            toReturn[currentIndex] = element;
            ++currentIndex;
        }

        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName) 
    {
        var warehouseListHelper = new ListHelper<Warehouse>();
        var filterList = warehouseListHelper.FindAllThat(warehouseList, (e) -> e.GetStateName() == stateName);
        var toReturn = new Warehouse[filterList.size()];
        int currentIndex = 0;
        for(var element : filterList)
        {
            if(element == null)
            {
                continue;
            }

            toReturn[currentIndex] = element;
            ++currentIndex;
        }

        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName, String streetName) 
    {
        var warehouseArrayHelper = new ArrayHelper<Warehouse>();
        var withStateName = GetWarehouses(stateName);

        var filterList = warehouseArrayHelper.FindAll(withStateName, (e) -> e.GetStreetName() == streetName);
        var toReturn = new Warehouse[filterList.size()];
        int currentIndex = 0;
        for(var element : filterList)
        {
            if(element == null)
            {
                continue;
            }

            toReturn[currentIndex] = new Warehouse(element);
            ++currentIndex;
        }

        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehousesByOwnerCpf(String ownerCpf) 
    {
        var warehouseListHelper = new ListHelper<Warehouse>();
        var filterList = warehouseListHelper.FindAllThat(warehouseList, (e) -> e.GetOwner().GetCpf() == ownerCpf);
        var toReturn = new Warehouse[filterList.size()];
        int currentIndex = 0;
        for(var element : filterList)
        {
            if(element == null)
            {
                continue;
            }

            toReturn[currentIndex] = element;
            ++currentIndex;
        }
        return toReturn;
    }

    @Override
    public Warehouse GetWarehouse(String id) 
    {
        var warehouseListHelper = new ListHelper<Warehouse>();
        var toCopyWarehouse = warehouseListHelper.Find(warehouseList, (e) -> e.GetId() == id);
        if(toCopyWarehouse == null)
        {
            System.out.println("Warehouse with id "+id+" is not in register. Fail to get warehouse");
            return null;
        }

        return new Warehouse(toCopyWarehouse); 
    }

    @Override
    public Warehouse GetWarehouse(String stateName, String streetName, int number) 
    {
        var warehouseArrayHelper = new ArrayHelper<Warehouse>();
        var withStateNameAndStreetName = GetWarehouses(stateName, streetName);
        var toReturn = warehouseArrayHelper.Find(withStateNameAndStreetName, (e) -> e.GetNumber() == number);

        return toReturn;
    }

    @Override
    public Boolean TryRegisterWarehouse(Warehouse warehouse, String beginDate) 
    {
        // Deve-se verificar se já existe um galpão com mesmo id
        // Caso a pessoa dona do galpão não esteja na tabela pessoa, deve-se a adicionar
        // Lembrar de preencher a tabela de relacionamento isWarehouseOwnerList

        var warehouseListHelper = new ListHelper<Warehouse>();
        if(warehouseListHelper.Exists(warehouseList, (e) -> e.GetId() == warehouse.GetId()))
        {
            System.out.println("A warehouse with the same id "+warehouse.GetId()+" already is in register. Fail to register.");
            return false;
        }

        isWarehouseOwnerList.add(new IsWarehouseOwner(warehouse.GetId(), warehouse.GetOwner().GetCpf(), beginDate));
        var personListHelper = new ListHelper<Person>();
        if(!personListHelper.Exists(personList, (e) -> e.GetCpf() == warehouse.GetOwner().GetCpf()))
        {
            personList.add(warehouse.GetOwner());
        }

        warehouseList.add(warehouse);
        return true;
    }

    @Override
    public Boolean TryUpdateWarehouse(Warehouse warehouse, String date) 
    {
        var warehouseListHelper = new ListHelper<Warehouse>();
        if(!warehouseListHelper.Exists(warehouseList, (e) -> e.GetId() == warehouse.GetId()))
        {
            System.out.println("The warehouse with id "+warehouse.GetId()+" is not in register. Fail to update.");
            return false;
        }
        var plh = new ListHelper<Person>();
        System.out.println("Person Table:");
        plh.ForAllDo(personList, (e) -> System.out.println(e.GetCpf()));
        var toUpdate = warehouseListHelper.Find(warehouseList, (e) -> e.GetId() == warehouse.GetId());
        var oldOwnerCpf = toUpdate.GetOwner().GetCpf();
        var newOwnerCpf = warehouse.GetOwner().GetCpf();
        // Precisa-se saber se o antigo dono está a ser referenciado por alguma outra tupla.
        // Caso não esteja, deve ser removido do banco.
        // Caso esteja, deve-se setar a data de fim na tupla em isWarehouseOwnerList.
        System.out.println("Old owner cpf: "+oldOwnerCpf);
        System.out.println("New owner cpf: "+newOwnerCpf);
        if(oldOwnerCpf != newOwnerCpf) 
        {
            var numberOfReferences = GetNumberOfReferences(oldOwnerCpf);
            System.out.println("Number of references to the old owner: "+numberOfReferences);
            if(numberOfReferences == 1)
            {
                var personListHelper = new ListHelper<Person>();
                personList.remove(personListHelper.GetIndexOf(personList, (e) -> e.GetCpf() == oldOwnerCpf));
            }
            else
            {
                var isWarehouseOwnerListHelper = new ListHelper<IsWarehouseOwner>();
                var toUp = isWarehouseOwnerListHelper.Find
                (
                    isWarehouseOwnerList, 
                    (e) -> e.GetOwnerCpf() == oldOwnerCpf && e.GetWarehouseId() == warehouse.GetId()
                );

                toUp.SetEndDate(date);
            }
        }

        // Caso o novo dono não esteja cadastrado
        var personListHelper = new ListHelper<Person>();
        if(!personListHelper.Exists(personList, (e) -> e.GetCpf() == warehouse.GetOwner().GetCpf()))
        {
            personList.add(warehouse.GetOwner());
        }
        
        isWarehouseOwnerList.add(new IsWarehouseOwner(warehouse.GetId(), newOwnerCpf, date));
        System.out.println("Person Table:");
        plh.ForAllDo(personList, (e) -> System.out.println(e.GetCpf()));
        toUpdate.CopyAttributesOf(warehouse);
        return true;
    }

    @Override
    public Boolean TryRemoveWarehouse(String id) 
    {
        // Caso o galpão com id não esteja registrado, uma mensagem de erro deve ser informada e a função deve retornar falso
        // Caso o dono do galpão removido não esteja referenciado em nenhuma outra tabela, deve-se o remover do banco
        var warehouseListHelper = new ListHelper<Warehouse>();
        if(!warehouseListHelper.Exists(warehouseList, (e) -> e.GetId() == id))
        {
            System.out.println("The warehouse with id "+id+" is not in register. Fail to remove.");
            return false;
        }
        var plh = new ListHelper<Person>();
        System.out.println("To remove: "+id);
        System.out.println("Person table");
        plh.ForAllDo(personList, (e) -> System.out.println(e.GetCpf())); 
        
        isWarehouseOwnerList.remove(new ListHelper<IsWarehouseOwner>().GetIndexOf
        (
            isWarehouseOwnerList, (e) -> e.GetWarehouseId() == id
        ));
            
        var ownerCpf = warehouseListHelper.Find(warehouseList, (e) -> e.GetId() == id).GetOwner().GetCpf();
        if(GetNumberOfReferences(ownerCpf) == 0)
        {
            var personListHelper = new ListHelper<Person>();
            personList.remove(personListHelper.GetIndexOf
            (
                personList, (e) -> e.GetCpf() == ownerCpf
            ));
        }
        System.out.println("To remove: "+id);
        System.out.println("Person table");
        plh.ForAllDo(personList, (e) -> System.out.println(e.GetCpf())); 
        var toRemoveIndex = warehouseListHelper.GetIndexOf(warehouseList, (e) -> e.GetId() == id);
        warehouseList.remove(toRemoveIndex);
        return true;
    }

    @Override
    public Property[] GetProperties() 
    {    
        var toReturn = new Property[propertyList.size()];
        
        for(var i = 0; i < propertyList.size(); ++i)
        {
            toReturn[i] = new Property(propertyList.get(i));
        }

        return toReturn;
    }

    @Override
    public Property GetPropertyById(String id) 
    {
        var propertyListHelper = new ListHelper<Property>();
        var toReturn = propertyListHelper.Find(propertyList, (e) -> e.GetId() == id);
        if(toReturn == null)
        {
            System.out.println("Fail to get property with id "+id);
        }

        return toReturn;
    }

    @Override
    public Boolean TryRegisterProperty(Property property) 
    {
        var propertyListHelper = new ListHelper<Property>();
        if(propertyListHelper.Exists(propertyList, (e) -> e.GetId() == property.GetId()))
        {
            System.out.println("Property with same ID already in register");
            return false;
        }

        propertyList.add(property);
        return true;
    }

    @Override
    public Boolean TryUpdateProperty(Property property) 
    {
        var propertyListHelper = new ListHelper<Property>();
        if(!propertyListHelper.Exists(propertyList, (e) -> e.GetId() == property.GetId()))
        {
            System.out.println("Property with id "+property.GetId()+" is not registered. Fail to update");
            return false;
        }

        propertyList.set
        (
            propertyListHelper.GetIndexOf(propertyList, (e) -> e.GetId() == property.GetId()), 
            property
        );
        return true;
    }

    @Override
    public Boolean TryRemoveProperty(String id) 
    {
        var propertyListHelper = new ListHelper<Property>();
        if(!propertyListHelper.Exists(propertyList, (e) -> e.GetId() == id))
        {
            System.out.println("Property with id "+id+" is not registered. Fail to remove.");
            return false;
        }

        propertyList.remove(propertyListHelper.GetIndexOf(propertyList, (e) -> e.GetId() == id));
        return true;
    }

    // Busca por referências (chaves estrangeiras) a um cpf. As tabelas que são varridas podem ser identificadas no corpo da função
    private int GetNumberOfReferences(String cpf)
    {
        var worksOnListHelper = new ListHelper<WorksOn>();
        var managerWarehouseListHelper = new ListHelper<ManageWarehouse>();
        var isWarehouseOwnerListHelper = new ListHelper<IsWarehouseOwner>();
        var buyBagListHelper = new ListHelper<BuyBag>();

        var inWorksOn = worksOnListHelper.FindAllThat(worksOnList, (e) -> e.GetCpf() == cpf);
        var inManageWarehouse = managerWarehouseListHelper.FindAllThat(manageWarehouseList, (e) -> e.GetManagerCpf() == cpf);
        var inIsWarehouseOwner = isWarehouseOwnerListHelper.FindAllThat(isWarehouseOwnerList, (e) -> e.GetOwnerCpf() == cpf);
        var inBuyBag = buyBagListHelper.FindAllThat(buyBagList, (e) -> e.GetCpf() == cpf);
        
        return inWorksOn.size() + inManageWarehouse.size() + inIsWarehouseOwner.size() + inBuyBag.size();
    }
}
