package Frameworks.Database;

import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

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
        private String m_warehouseId = new String();
        private String m_managerCpf = new String();
        private String m_beginDate = new String();
        private String m_endDate = new String();

        public ManageWarehouse
        (
            String warehouseId,
            String managerCpf,
            String beginDate
        )
        {
            m_warehouseId = String.copyValueOf(warehouseId.toCharArray());
            m_managerCpf = String.copyValueOf(managerCpf.toCharArray());
            m_beginDate = String.copyValueOf(beginDate.toCharArray());
        }

        public String GetWarehouseId()
        {
            return new String(m_warehouseId);
        }

        public String GetManagerCpf()
        {
            return new String(m_managerCpf);
        }

        public String GetBeginDate()
        {
            return new String(m_beginDate);
        }

        public String GetEndDate()
        {
            return new String(m_endDate);
        }

        public void SetEndDate(String endDate)
        {
            m_endDate = String.copyValueOf(endDate.toCharArray());
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
        private String m_cpf = new String();
        private String m_propertyId = new String();
        private String m_beginDate = new String();
        private String m_endDate = new String();

        public WorksOn
        (
            String cpf,
            String propertyId,
            String beginDate
        )
        {
            m_cpf = String.copyValueOf(cpf.toCharArray());
            m_propertyId = String.copyValueOf(propertyId.toCharArray());
            m_beginDate = String.copyValueOf(beginDate.toCharArray());
        }

        public String GetCpf()
        {
            return new String(m_cpf);
        }

        public String GetPropertyId()
        {
            return new String(m_propertyId);
        }

        public String GetBeginDate()
        {
            return new String(m_beginDate);
        }

        public String GetEndDate()
        {
            return new String(m_endDate);
        }

        public void SetEndDate(String endDate)
        {
            m_endDate = String.copyValueOf(endDate.toCharArray());
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

    private List<User> m_userList = new ArrayList<User>();
    private List<Person> m_personList = new ArrayList<Person>();
    private List<Warehouse> m_warehouseList = new ArrayList<Warehouse>();
    private List<ManageWarehouse> m_manageWarehouseList = new ArrayList<ManageWarehouse>();
    private List<IsWarehouseOwner> m_isWarehouseOwnerList = new ArrayList<IsWarehouseOwner>();
    private List<WorksOn> m_worksOnList = new ArrayList<WorksOn>();
    private List<BuyBag> m_buyBagList = new ArrayList<BuyBag>();
    private List<Property> m_propertyList = new ArrayList<Property>();
    private List<Batch> m_batchList = new ArrayList<Batch>();
    private List<CoffeeBag> m_coffeeBagList = new ArrayList<CoffeeBag>();

    // Usuários não são cadastrados pela aplicação. Eles deve estar previamente registrados!
    public void AddUser(User user)
    {
        m_userList.add(user);
    }

    @Override
    public Boolean TryLogin(String login, String password) 
    {
        var iterator = m_userList.iterator();
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
    public Batch[] GetBatches() 
    {
        var toReturn = new Batch[m_batchList.size()];
        for(var i = 0; i < m_batchList.size(); ++i)
        {
            toReturn[i] = m_batchList.get(i);
        }

        return toReturn;
    }

    @Override
    public Batch GetBatch(String batchId) 
    {
        // Verifica-se se existe um lote com id batchId
        // Se não existir, exibe-se uma mensagem de erro e se retorna nulo
        // Caso contrário, retorna-se o lote com esse ID 
        
        var batchListHelper = new ListHelper<Batch>();
        if(!batchListHelper.Exists(m_batchList, (e) -> e.GetId() == batchId))
        {
            System.out.println("The batch with id: "+batchId+" is not in register");
            System.out.println("Fail to get batch");
            return null;
        }

        return new Batch(batchListHelper.Find(m_batchList, (e) -> e.GetId() == batchId));
    }

    @Override
    public Boolean TryRegisterBatch(Batch batch) 
    {
        // Apenas se precisa verificar se um lote com o id em batch já está cadastrado
        // Se já estiver, exibe-se uma mensagem de erro se retorna falso
        // Caso contrário, insere-se o lote na tabela se retorna verdadeiro

        var batchListHelper = new ListHelper<Batch>();
        if(batchListHelper.Exists(m_batchList, (e) -> e.GetId().equals(batch.GetId())))
        {
            System.out.println("Batch with id "+batch.GetId()+" is already in register");
            System.out.println("Fail to register");
            return false;
        }

        m_batchList.add(batch);
        return true;
    }

    @Override
    public Boolean TryRemoveBatch(String batchId) 
    {
        // Deve-se verificar se um lote com id batchId está registrado.
        // Se estiver, remove-se-o e se retorna true 
        // Caso contrário, retorna-se falso. Uma mensagem de erro deve ser exibida 

        var batchListHelper = new ListHelper<Batch>();
        if(!batchListHelper.Exists(m_batchList, (e) -> e.GetId().equals(batchId)))
        {
            System.out.println("Batch with id: "+batchId+" is not in register");
            System.out.println("Fail to remove");
            return false;
        }

        m_batchList.removeIf((e) -> e.GetId().equals(batchId));
        return true;
    }

    @Override
    public CoffeeBag[] GetCoffeeBags() 
    {
        if(m_coffeeBagList.size() == 0)
        {
            return null;
        }

        var toReturn = new CoffeeBag[m_coffeeBagList.size()];
        for(var i = 0; i < m_coffeeBagList.size(); ++i)
        {
            toReturn[i] = m_coffeeBagList.get(i);
        }

        return toReturn;
    }

    @Override
    public CoffeeBag[] GetCoffeeBags(String batchId) 
    {
        // Verificar se o lote com id batchId existe
        // Se não existir, deve-se imprimir uma mensagem de erro e retornar nulo
        // Caso contrário, deve-se retornar todas as sacas de café nesse lote

        var batchListHelper = new ListHelper<Batch>();
        if(!batchListHelper.Exists(m_batchList, (e) -> e.GetId().equals(batchId)))
        {
            System.out.println("Batch with id: "+batchId+" is not in register");
            System.out.println("Fail to get coffee bags");
            return null;
        }

        var coffeeBagListHelper = new ListHelper<CoffeeBag>();
        var coffeeBagList = coffeeBagListHelper.FindAllThat(m_coffeeBagList, (e) -> e.GetBatch().GetId().equals(batchId));        
        var toReturn = new CoffeeBag[coffeeBagList.size()];
        for(var i = 0; i < coffeeBagList.size(); ++i)
        {
            toReturn[i] = coffeeBagList.get(i);
        }

        if(toReturn.length == 0)
        {
            System.out.println("No coffee bags with batch id "+batchId);
            System.out.println("Fail to get coffee bags");
            return null;
        }

        return toReturn;
    }

    @Override
    public CoffeeBag GetCoffeeBag(String batchId, String coffeeBagId) 
    {
        // Verificar se o lote com id batchId existe
        // Se não existir, deve-se imprimir uma mensagem de erro e retornar nulo
        // Se existir, deve-se verificar se uma saca de café com id coffeeBagId existe
        // Se não existir, deve-se umprimir uma mensagem de erro e retornar nulo
        // Se existir, deve-se retornar a saca de café

        var batchListHelper = new ListHelper<Batch>();
        if(!batchListHelper.Exists(m_batchList, (e) -> e.GetId() == batchId))
        {
            System.out.println("Batch with id: "+batchId+" is not in register");
            System.out.println("Fail to get coffee bag");
            return null;
        }

        var coffeeBagListHelper = new ListHelper<CoffeeBag>();
        if(!coffeeBagListHelper.Exists(m_coffeeBagList, (e) -> e.GetId() == coffeeBagId))
        {
            System.out.println("Coffee bag with id: "+batchId+" is not in register");
            System.out.println("Fail to get coffee bag");
            return null;
        }

        return new CoffeeBag(coffeeBagListHelper.Find(m_coffeeBagList, (e) -> e.GetId() == coffeeBagId));
    }

    @Override
    public Boolean TryRegisterCoffeeBag(CoffeeBag coffeeBag) 
    {
        // Deve-se verificar se o lote e o galpão já estão cadastrados no banco
        // Se algum desses não estiver, deve-se imprimir uma mensagem de erro e retornar falso
        // Se estiverem, deve-se verificar se já existe uma saca de café com o mesmo id de sace e id de lote na lista m_coffeeBagList
        // Se existir, deve-se imprimir uma mensagem de erro e retornar falso
        // Caso contrário, deve-se registrar a nova saca de café e retornar verdadeiro

        var batchListHelper = new ListHelper<Batch>();
        var warehouseListHelper = new ListHelper<Warehouse>();
        var coffeeBagListHelper = new ListHelper<CoffeeBag>();
        if(!batchListHelper.Exists(m_batchList, (e) -> e.GetId() == coffeeBag.GetBatch().GetId()))
        {
            System.out.println("Batch with id "+coffeeBag.GetBatch().GetId()+" is not in register");
            System.out.println("Fail to register coffee bag");
            return false;
        }

        if(!warehouseListHelper.Exists(m_warehouseList, (e) -> e.GetId() == coffeeBag.GetWarehouse().GetId()))
        {
            System.out.println("Warehouse with id "+coffeeBag.GetWarehouse().GetId()+" is not in register");
            System.out.println("Fail to register coffee bag");
            return false;
        }

        if
        (
            coffeeBagListHelper.Exists
            (
                m_coffeeBagList, 
                (e) -> e.GetBatch().GetId().equals(coffeeBag.GetBatch().GetId()) &&
                e.GetId().equals(coffeeBag.GetId())
            )
        )
        {
            System.out.println("Coffee bag with id "+coffeeBag.GetId()+" and batch id "+coffeeBag.GetBatch().GetId()+" is already in register");
            System.out.println("Fail to register coffee bag");
            return false;
        }

        m_coffeeBagList.add(new CoffeeBag(coffeeBag));
        return true;
    }

    @Override
    public Boolean TryRemoveCoffeeBag(String batchId, String coffeeBagId) 
    {
        // Deve-se verificar se o lote com id batchId existe, bem como se a saca com id coffeeBagId existe
        // Se qualquer um desses dois não existirem, deve-se imprimir mensagens de erro e retornar falso
        // Se ambos existirem, deve-se remover a saca com id de saca coffeeBagId e id de lote batchId

        var batchListHelper = new ListHelper<Batch>();
        var coffeeBagListHelper = new ListHelper<CoffeeBag>();
        if(!batchListHelper.Exists(m_batchList, (e) -> e.GetId().equals(batchId)))
        {
            System.out.println("Batch with id "+batchId+" is not in register");
            System.out.println("Fail to remove coffee bag");
            return false;
        }

        if(!coffeeBagListHelper.Exists(m_coffeeBagList, (e) -> e.GetId().equals(coffeeBagId)))
        {
            System.out.println("Coffee bag with id "+coffeeBagId+" is not in register");
            System.out.println("Fail to remove coffee bag");
            return false;
        }

        m_coffeeBagList.removeIf((e) -> e.GetId().equals(coffeeBagId) && e.GetBatch().GetId().equals(batchId));
        return true;
    }

    @Override
    public Employee[] GetEmployees() 
    {
        // Os funcionários são as pessoas cujos cpf's são "mencionados" nas tabelas worksOn e ManageWarehouse
        // Valores de chaves repedidos devem ser descartados.
        var cpfList = new ArrayList<String>();
        var stringListHelper = new ListHelper<String>();

        for(var element : m_worksOnList)
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

        for(var element : m_manageWarehouseList)
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

        if(cpfList.size() == 0)
        {
            return null;
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

            var employee = (Employee) personListHelper.Find(m_personList, (e) -> e.GetCpf().equals(cpf));
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
            worksOnListHelper.Exists(m_worksOnList, (e) -> e.GetCpf().equals(cpf)) || 
            manageWarehouseListHelper.Exists(m_manageWarehouseList, (e) -> e.GetManagerCpf().equals(cpf))
        )
        {
            var personListHelper = new ListHelper<Person>();
            var employee = (Employee) personListHelper.Find(m_personList, (e) -> e.GetCpf().equals(cpf));
            return Employee.GetEmployeeCopy(employee);
        }
        
        System.out.println("A employee with cpf "+cpf+" is not in register");
        System.out.println("Fail to read");
        
        return null;
    }

    public Servant[] GetServants()
    {
        // Deve-se verificar os cpf's que estão registrados na tabela worksOn
        // Com isso, obtem-se os objetos da tabela person

        var cpfList = new ArrayList<String>();
        for(var tuple : m_worksOnList)
        {
            if(tuple == null)
            {
                continue;
            }

            cpfList.add(tuple.GetCpf());
        }

        if(cpfList.isEmpty())
        {
            System.out.println("No servant in register");
            return null;
        }
        
        var toReturn = new Servant[cpfList.size()];
        int index = -1;
        var personListHelper = new ListHelper<Person>();
        for(var cpf : cpfList)
        {
            ++index;
            if(cpf == null)
            {
                continue;
            }

            toReturn[index] = (Servant) personListHelper.Find(m_personList, (e) -> e.GetCpf().equals(cpf));
        }

        return toReturn;
    }    

    public Servant GetServant(String cpf)
    {
        // Deve-se verificar se o cpf pertence a um servente - busca-se pelo cpf na tabela worksOn
        // Caso não esteja nessa tabela, uma mensagem de erro precisa ser exibida e retornar falso
        // Caso esteja, busca-se pelo objeto na tabela pessoa

        var worksOnListHelper = new ListHelper<WorksOn>();
        if(!worksOnListHelper.Exists(m_worksOnList, (e) -> e.GetCpf().equals(cpf)))
        {
            System.out.println("There no servant with cpf "+cpf+" in register");
            System.out.println("Fail to read servant");
            return null;
        }

        var personListHelper = new ListHelper<Person>();
        var servant = (Servant) personListHelper.Find(m_personList, (e) -> e.GetCpf().equals(cpf));

        return new Servant(servant);
    }
    public WarehouseManager[] GetWarehouseManagers()
    {
        var cpfList = new ArrayList<String>();
        for(var tuple : m_manageWarehouseList)
        {
            if(tuple == null)
            {
                continue;
            }

            cpfList.add(tuple.GetManagerCpf());
        }

        if(cpfList.isEmpty())
        {
            System.out.println("No warehouse manager in register");
            return null;
        }
        
        var toReturn = new WarehouseManager[cpfList.size()];
        int index = -1;
        var personListHelper = new ListHelper<Person>();
        for(var cpf : cpfList)
        {
            ++index;
            if(cpf == null)
            {
                continue;
            }

            toReturn[index] = (WarehouseManager) personListHelper.Find(m_personList, (e) -> e.GetCpf().equals(cpf));
        }

        return toReturn;
    }
    public WarehouseManager GetWarehouseManager(String cpf)
    {
        // Precisa-se verificar se o cpf ewxiste na tabela manageWarehouse
        // Caso não exista, deve-se imprimir uma mensagem de erro e retornar nulo
        // Caso exista, deve-se procurar e retornar o objeto na tabela person

        var manageWarehouseListHelper = new ListHelper<ManageWarehouse>();
        if(!manageWarehouseListHelper.Exists(m_manageWarehouseList, (e) -> e.GetManagerCpf().equals(cpf)))
        {
            System.out.println("There no warehouse manager with cpf "+cpf+" in register");
            System.out.println("Fail to read warehouse manager");
            return null;
        }

        var personListHelper = new ListHelper<Person>();
        var warehouseManager = (WarehouseManager) personListHelper.Find(m_personList, (e) -> e.GetCpf().equals(cpf));
        return new WarehouseManager(warehouseManager);
    }

    @Override
    public Boolean TryRegisterServant(Servant servant, String date) 
    {
        // Verificar se a propriedade está cadastrada
        // Se não estiver, uma mensagem de erro deve ser exibida e a função retorna falso
        // Verificar se o servente está cadastrado nas tabelas pessoa e worksOn
        // Se já estiver na tabela worksOn, uma mensagem de erro deve ser exibida e a função retorna falso
        // Se já estiver somente na tabela pessoa, deve-se verificar se esse cpf está incluído na tabela
        // de gerência de galpão
        // Se estiver, uma mensagem de erro deve ser exibida e a função retorna falso
        // Caso contrário, faz-se o cadastro do servente, pelo menos, na tabela woksOn

        var newServantCpf = servant.GetCpf();
        var newServantProperty = servant.GetProperty();

        var propertyListHelper = new ListHelper<Property>();
        if(!propertyListHelper.Exists(m_propertyList, (e) -> e.GetId().equalsIgnoreCase(newServantProperty.GetId())))
        {
            System.out.println("Propety with id "+newServantProperty.GetId()+" is not in register");
            System.out.println("Fail to register servant with cpf "+newServantCpf);
            return false;
        }

        var worksOnListHelper = new ListHelper<WorksOn>();
        if(worksOnListHelper.Exists(m_worksOnList, (e) -> e.GetCpf().equalsIgnoreCase(newServantCpf)))
        {
            System.out.println("Servant with cpf "+newServantCpf+" already in register");
            System.out.println("Fail to register servant with cpf "+newServantCpf);
            return false;
        }

        var personListHelper = new ListHelper<Person>();
        if(personListHelper.Exists(m_personList, (e) -> e.GetCpf().equals(newServantCpf)))
        {
            var manageWarehouseListHelper = new ListHelper<ManageWarehouse>();
            if(manageWarehouseListHelper.Exists(m_manageWarehouseList, (e) -> e.GetManagerCpf().equals(newServantCpf)))
            {
                System.out.println("The employee with cpf "+newServantCpf+" is alredy working as warehouse manager");
                System.out.println("Fail to register servant with cpf "+newServantCpf);
                return false;
            }

            var isWarehouseOwnerListHelper = new ListHelper<IsWarehouseOwner>();
            if(isWarehouseOwnerListHelper.Exists(m_isWarehouseOwnerList, (e) -> e.GetOwnerCpf().equals(newServantCpf)))
            {
                System.out.println("A warehouse owner with cpf "+newServantCpf+" is in register");
                System.out.println("Fail to register servant with cpf "+newServantCpf);
                return false;
            }

            m_personList.removeIf((e) -> e.GetCpf().equals(newServantCpf));
        }

        m_personList.add(new Servant(servant));
        m_worksOnList.add(new WorksOn(newServantCpf, servant.GetProperty().GetId(), date));
        
        return true;
    }

    @Override
    public Boolean TryRegisterWarehouseManager(WarehouseManager warehouseManager, String beginDate) 
    {
        // Caso já exista um registro na tabela manageWarehouse com o cpf de warehouseManager, 
        // deve ser exibida uma mensagem de erro e retornar falso
        // Caso o galpão não exista, deve ser exibida uma mensagem de erro e retornar falso
        // Deve-se verificar se alguém já administra o galpão
        // Caso positivo, deve ser exibida uma mensagem de erro e retornar falso
        // Caso contrário, deve-se verificar se já existe um registro do novo cpf na tabela person
        // Caso já exista, deve-se verificar se ele não é de um servente ou de um DONO DE GALPÃO
        // Caso seja, deve ser exibida uma mensagem de erro e retornar falso
        // Lembrar que o gerente de galpão já pode ter um registro na tabela person
        // Nesse caso, deve-se apenas substituir o objeto

        var newWarehouseManagerCpf = warehouseManager.GetCpf();
        var manageWarehouseListHelper = new ListHelper<ManageWarehouse>();
        if
        (
            manageWarehouseListHelper.Exists
            (
                m_manageWarehouseList, 
                (e) -> e.GetManagerCpf().equals(newWarehouseManagerCpf)
            )
        )
        {
            System.out.println("Warehouse manager with cpf "+newWarehouseManagerCpf+" is already in register");
            System.out.println("Fail to register warehouse manager with cpf "+newWarehouseManagerCpf);
            return false;
        }

        var warehouseId = warehouseManager.GetWarehouse().GetId();
        var warehouseListHelper = new ListHelper<Warehouse>();
        if(!warehouseListHelper.Exists(m_warehouseList, (e) -> e.GetId().equals(warehouseId)))
        {
            System.out.println("Warehouse with id "+warehouseId+" is not in register");
            System.out.println("Fail to register warehouse manager with cpf "+newWarehouseManagerCpf);
            return false;
        }

        if
        (
            manageWarehouseListHelper.Exists
            (
                m_manageWarehouseList, 
                (e) -> e.GetWarehouseId().equals(warehouseId) &&
                e.GetEndDate().isEmpty()
            )
        )
        {
            var tuple = manageWarehouseListHelper.Find
            (
                m_manageWarehouseList, 
                (e) -> e.GetWarehouseId().equals(warehouseId) &&
                e.GetEndDate().isEmpty()
            );

            System.out.println
            (
                "Warehouse with id "+warehouseId+" is already managed by "+
                "warehouse manager with cpf "+tuple.GetManagerCpf()
            );
            System.out.println("Fail to register warehouse manager with cpf "+newWarehouseManagerCpf);
            return false;
        }

        var personListHelper = new ListHelper<Person>();
        if(!personListHelper.Exists(m_personList, (e) -> e.GetCpf().equals(newWarehouseManagerCpf)))
        {
            m_personList.add(new WarehouseManager(warehouseManager));
        }
        else 
        {
            var worksOnListHelper = new ListHelper<WorksOn>();
            if(worksOnListHelper.Exists(m_worksOnList, (e) -> e.GetCpf().equals(newWarehouseManagerCpf)))
            {
                System.out.println("The employee with cpf "+newWarehouseManagerCpf+" is already a servant");
                System.out.println("Fail to register warehouse manager with cpf "+newWarehouseManagerCpf);
                return false;
            }

            var isWarehouseOwnerListHelper = new ListHelper<IsWarehouseOwner>();
            if(isWarehouseOwnerListHelper.Exists(m_isWarehouseOwnerList, (e) -> e.GetOwnerCpf().equals(newWarehouseManagerCpf)))
            {
                System.out.println("A warehouse owner with cpf "+newWarehouseManagerCpf+" is in register");
                System.out.println("Fail to register warehouse manager with cpf "+newWarehouseManagerCpf);
                return false;
            }

            m_personList.removeIf((e) -> e.GetCpf().equals(newWarehouseManagerCpf));
            m_personList.add(new WarehouseManager(warehouseManager));
        }
        m_manageWarehouseList.add
        (
            new ManageWarehouse
            (
                warehouseId, 
                newWarehouseManagerCpf, 
                beginDate
            )
        );

        return true;
    }

    @Override
    public Boolean TryUpdateServant(Servant servant, String date) 
    {
        // Deve-se verificar se o cpf do servente servant está cadastrado e se é de um servente
        // Se não for, uma mensagem de erro deve ser imprimida e retornar falso
        // Para o caso da propriedade mudar, deve-se verificar se a nova propriedade existe no banco
        // Caso não exista, uma mensagem de erro deve ser imprimida e retornar falso
        // Caso exista, deve-se setar a data de fim na tupla da tabela worksOn e cadastrar a nova tupla
        // Caso as propriedades sejam iguais, simplemente fazer a atualização

        var worksOnListHelper = new ListHelper<WorksOn>();
        if(!worksOnListHelper.Exists(m_worksOnList, (e) -> e.GetCpf().equals(servant.GetCpf())))
        {
            System.out.println("Servant with cpf "+servant.GetCpf()+" not in register");
            System.out.println("Fail to update");
            return false;
        }

        var personListHelper = new ListHelper<Person>();

        // referência para o objeto na tabela
        var inDatabaseServant = (Servant) personListHelper.Find
        (
            m_personList, 
            (e) -> e.GetCpf().equals(servant.GetCpf())
        );
        if(!inDatabaseServant.GetProperty().equals(servant.GetProperty()))
        {
            var propertyListHelper = new ListHelper<Property>();
            if(!propertyListHelper.Exists(m_propertyList, (e) -> e.equals(servant.GetProperty())))
            {
                System.out.println("Property with id "+servant.GetProperty().GetId()+" is not in register");
                System.out.println("Fail to add servant");
                return false;
            }

            var tuple = worksOnListHelper.Find
            (
                m_worksOnList, 
                (e) -> e.GetCpf().equals(servant.GetCpf()) &&
                e.GetEndDate().isEmpty()
            );
            tuple.SetEndDate(date);

            m_worksOnList.add
            (
                new WorksOn
                (
                    servant.GetCpf(), 
                    servant.GetProperty().GetId(), 
                    date
                )
            );
        }
        
        inDatabaseServant.CopyValuesOf(servant);
        return true;
    }

    @Override
    public Boolean TryUpdateWarehouseManager(WarehouseManager warehouseManager, String date) 
    {
        // Caso o cpf informado não seja de um gerente, deve-se imprimir uma mensagem e retornar falso
        // Caso o galpão mude, deve-se verificar se o id do novo está cadastrado
        // Caso não esteja, deve-se imprimir uma mensagem de erro e retornar falso
        // Caso esteja, deve-se verificar se o galpão já é gerenciado por alguém
        // Caso seja, deve-se imprimir uma mensagem de erro e retornar falso
        // Caso não seja, fazer as atualizações (maganeWarehouse e Employee)
        // Lembrar de setar a data de fim caso o galpão mude

        var warehouseManagerCpf = warehouseManager.GetCpf();
        var warehouseManagerWarehouse = warehouseManager.GetWarehouse();

        var manageWarehouseListHelper = new ListHelper<ManageWarehouse>();
        if(!manageWarehouseListHelper.Exists(m_manageWarehouseList, (e) -> e.GetManagerCpf().equals(warehouseManagerCpf)))
        {
            System.out.println("Warehouse manager with cpf "+warehouseManagerCpf+" not in register");
            System.out.println("Fail to update warehouse manager");
            return false;
        }

        var personListHelper = new ListHelper<Person>();
        var inDatabaseWarehouseManager = (WarehouseManager) personListHelper.Find
        (
            m_personList, 
            (e) -> e.GetCpf().equals(warehouseManagerCpf)
        ); 

        if(!inDatabaseWarehouseManager.GetWarehouse().equals(warehouseManagerWarehouse))
        {
            var warehouseListHelper = new ListHelper<Warehouse>();
            if(!warehouseListHelper.Exists(m_warehouseList, (e) -> e.equals(warehouseManagerWarehouse)))
            {
                System.out.println("Warehouse with id "+warehouseManagerWarehouse.GetId()+" not in register");
                System.out.println("Fail to update warehouse manager");
                return false;
            }

            if
            (
                manageWarehouseListHelper.Exists
                (
                    m_manageWarehouseList, 
                    (e) -> e.GetWarehouseId().equals(warehouseManagerWarehouse.GetId()) &&
                    e.GetEndDate().isEmpty()
                )
            )
            {
                var tuple = manageWarehouseListHelper.Find
                (
                    m_manageWarehouseList, 
                    (e) -> e.GetWarehouseId().equals(warehouseManagerWarehouse.GetId()) &&
                    e.GetEndDate().isEmpty()
                );
                System.out.println
                (
                    "The warehouse with id "+warehouseManagerWarehouse.GetId()+
                    " is already beeing managed by manager with cpf "+tuple.GetManagerCpf()
                );
                return false;
            }

            var inDatabaseManageWarehouse = manageWarehouseListHelper.Find
            (
                m_manageWarehouseList, 
                (e) -> e.GetManagerCpf().equals(warehouseManagerCpf) && 
                e.GetEndDate().isEmpty()
            ); 

            if(inDatabaseManageWarehouse != null)
            {
                inDatabaseManageWarehouse.SetEndDate(date);
            }

            m_manageWarehouseList.add
            (
                new ManageWarehouse
                (
                    warehouseManagerWarehouse.GetId(), 
                    warehouseManagerCpf, 
                    date
                )
            );
        }

        inDatabaseWarehouseManager.CopyValuesOf(warehouseManager);
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
            !worksOnListHelper.Exists(m_worksOnList, (e) -> e.GetCpf().equals(cpf)) &&
            !managerWarehouseListHelper.Exists(m_manageWarehouseList, (e) -> e.GetManagerCpf().equals(cpf)
        ))
        {
            System.out.println("The cpf "+cpf+" is not owned by a employee. Fail to remove.");
            return false;
        }

        m_worksOnList.removeIf((e) -> e.GetCpf().equals(cpf));
        m_manageWarehouseList.removeIf((e) -> e.GetManagerCpf().equals(cpf));
        
        if(GetNumberOfReferences(cpf) == 0)
        {
            m_personList.removeIf((e) -> e.GetCpf().equals(cpf));
        }

        return true;
    }

    @Override
    public Warehouse[] GetWarehouses() 
    {
        var toReturn = new Warehouse[m_warehouseList.size()];
        int currentIndex = 0;
        for(var element : m_warehouseList)
        {
            toReturn[currentIndex] = new Warehouse(element);
            ++currentIndex;
        }

        if(toReturn.length == 0)
        {
            return null;
        }

        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName) 
    {
        var warehouseListHelper = new ListHelper<Warehouse>();
        var filterList = warehouseListHelper.FindAllThat(m_warehouseList, (e) -> e.GetStateName().equals(stateName));
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

        if(toReturn.length == 0)
        {
            return null;
        }

        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehouses(String stateName, String streetName) 
    {
        var warehouseArrayHelper = new ArrayHelper<Warehouse>();
        var withStateName = GetWarehouses(stateName);

        var filterList = warehouseArrayHelper.FindAll(withStateName, (e) -> e.GetStreetName().equals(streetName));
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

        if(toReturn.length == 0)
        {
            return null;
        }

        return toReturn;
    }

    @Override
    public Warehouse[] GetWarehousesByOwnerCpf(String ownerCpf) 
    {
        var warehouseListHelper = new ListHelper<Warehouse>();
        var filterList = warehouseListHelper.FindAllThat(m_warehouseList, (e) -> e.GetOwner().GetCpf().equals(ownerCpf));
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

        if(toReturn.length == 0)
        {
            return null;
        }

        return toReturn;
    }

    @Override
    public Warehouse GetWarehouse(String id) 
    {
        var warehouseListHelper = new ListHelper<Warehouse>();
        var toCopyWarehouse = warehouseListHelper.Find(m_warehouseList, (e) -> e.GetId().equals(id));
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
        // Caso a pessoa dona do galpão não esteja na tabela pessoa, deve-se a adicionar a adicionar
        // Caso esteja, deve-se verificar se ela não é servente e nem gerente de galpão
        // Caso seja qualquer um desses, uma mensagem de erro prepcisa ser exibida e retornar falso
        // Lembrar de preencher a tabela de relacionamento isWarehouseOwnerList

        var warehouseOwnerCpf = warehouse.GetOwner().GetCpf();

        var warehouseListHelper = new ListHelper<Warehouse>();
        if(warehouseListHelper.Exists(m_warehouseList, (e) -> e.GetId().equals(warehouse.GetId())))
        {
            System.out.println("A warehouse with the same id "+warehouse.GetId()+" already is in register");
            System.out.println("Fail to register warehouse");
            return false;
        }

        var personListHelper = new ListHelper<Person>();
        if(personListHelper.Exists(m_personList, (e) -> e.GetCpf().equals(warehouseOwnerCpf)))
        {
            // Verificar se é servente ou dono de galpão

            var worksOnListHelper = new ListHelper<WorksOn>();
            if(worksOnListHelper.Exists(m_worksOnList, (e) -> e.GetCpf().equals(warehouseOwnerCpf)))
            {
                System.out.println("The person with cpf "+warehouseOwnerCpf+" is already a servant");
                System.out.println("Fail to register warehouse");
                return false;
            }

            var managerWarehouseListHelper = new ListHelper<ManageWarehouse>();
            if(managerWarehouseListHelper.Exists(m_manageWarehouseList, (e) -> e.GetManagerCpf().equals(warehouseOwnerCpf)))
            {
                System.out.println("The person with cpf "+warehouseOwnerCpf+" is already a warehouse manager");
                System.out.println("Fail to register warehouse");
                return false;
            }
        }
        else
        {
            m_personList.add(new Person(warehouse.GetOwner()));
        }

        m_warehouseList.add(warehouse);
        m_isWarehouseOwnerList.add(new IsWarehouseOwner(warehouse.GetId(), warehouseOwnerCpf, beginDate));
        return true;
    }

    @Override
    public Boolean TryUpdateWarehouse(Warehouse warehouse, String date) 
    {
        // Deve-se seguir a mesma ideia do algoritmo para registro

        var warehouseListHelper = new ListHelper<Warehouse>();
        if(!warehouseListHelper.Exists(m_warehouseList, (e) -> e.GetId().equals(warehouse.GetId())))
        {
            System.out.println("The warehouse with id "+warehouse.GetId()+" is not in register. Fail to update.");
            return false;
        }
        var toUpdate = warehouseListHelper.Find(m_warehouseList, (e) -> e.GetId().equals(warehouse.GetId()));
        var oldOwnerCpf = toUpdate.GetOwner().GetCpf();
        var newOwnerCpf = warehouse.GetOwner().GetCpf();
        // Precisa-se saber se o antigo dono está a ser referenciado por alguma outra tupla.
        // Caso não esteja, deve ser removido do banco.
        // Caso esteja, deve-se setar a data de fim na tupla em isWarehouseOwnerList.

        if(oldOwnerCpf != newOwnerCpf) 
        {
            // Caso o novo dono não esteja cadastrado
            var personListHelper = new ListHelper<Person>();
            if(!personListHelper.Exists(m_personList, (e) -> e.GetCpf().equals(newOwnerCpf)))
            {
                m_personList.add(warehouse.GetOwner());
            }

            m_isWarehouseOwnerList.add(new IsWarehouseOwner(warehouse.GetId(), newOwnerCpf, date));
            var isWarehouseOwnerListHelper = new ListHelper<IsWarehouseOwner>();
            var numberOfReferences = GetNumberOfReferences(oldOwnerCpf);
            if(numberOfReferences == 1)
            {
                m_personList.remove(personListHelper.GetIndexOf(m_personList, (e) -> e.GetCpf().equals(oldOwnerCpf)));
                m_isWarehouseOwnerList.remove
                (
                    isWarehouseOwnerListHelper.Find
                    (
                        m_isWarehouseOwnerList, 
                        (e) -> e.GetWarehouseId().equals(warehouse.GetId()) && e.GetOwnerCpf().equals(oldOwnerCpf)
                    )
                );
            }
            else
            {
                // Atualização da tabela de relacionamento
                var toUp = isWarehouseOwnerListHelper.Find
                (
                    m_isWarehouseOwnerList, 
                    (e) -> e.GetOwnerCpf().equals(oldOwnerCpf) && e.GetWarehouseId().equals(warehouse.GetId())
                );

                toUp.SetEndDate(date);
            }
        }
    
        toUpdate.CopyAttributesOf(warehouse);
        return true;
    }

    @Override
    public Boolean TryRemoveWarehouse(String id) 
    {
        // Caso o galpão com id não esteja registrado, uma mensagem de erro deve ser informada e a função deve retornar falso
        // Caso o dono do galpão removido não esteja referenciado em nenhuma outra tabela, deve-se o remover do banco
        var warehouseListHelper = new ListHelper<Warehouse>();
        if(!warehouseListHelper.Exists(m_warehouseList, (e) -> e.GetId().equals(id)))
        {
            System.out.println("The warehouse with id "+id+" is not in register. Fail to remove.");
            return false;
        }
        var plh = new ListHelper<Person>();
        System.out.println("To remove: "+id);
        System.out.println("Person table");
        plh.ForAllDo(m_personList, (e) -> System.out.println(e.GetCpf())); 
        
        m_isWarehouseOwnerList.remove(new ListHelper<IsWarehouseOwner>().GetIndexOf
        (
            m_isWarehouseOwnerList, (e) -> e.GetWarehouseId().equals(id)
        ));
            
        var ownerCpf = warehouseListHelper.Find(m_warehouseList, (e) -> e.GetId().equals(id)).GetOwner().GetCpf();
        if(GetNumberOfReferences(ownerCpf) == 0)
        {
            var personListHelper = new ListHelper<Person>();
            m_personList.remove(personListHelper.GetIndexOf
            (
                m_personList, (e) -> e.GetCpf() == ownerCpf
            ));
        }
        System.out.println("To remove: "+id);
        System.out.println("Person table");
        plh.ForAllDo(m_personList, (e) -> System.out.println(e.GetCpf())); 
        var toRemoveIndex = warehouseListHelper.GetIndexOf(m_warehouseList, (e) -> e.GetId().equals(id));
        m_warehouseList.remove(toRemoveIndex);
        return true;
    }

    @Override
    public Property[] GetProperties() 
    {    
        var toReturn = new Property[m_propertyList.size()];
        
        for(var i = 0; i < m_propertyList.size(); ++i)
        {
            toReturn[i] = new Property(m_propertyList.get(i));
        }

        return toReturn;
    }

    @Override
    public Property GetPropertyById(String id) 
    {
        var propertyListHelper = new ListHelper<Property>();
        var toReturn = propertyListHelper.Find(m_propertyList, (e) -> e.GetId() == id);
        if(toReturn == null)
        {
            System.out.println("Fail to get property with id "+id);
            return null;
        }

        return new Property(toReturn);
    }

    @Override
    public Boolean TryRegisterProperty(Property property) 
    {
        var propertyListHelper = new ListHelper<Property>();
        if(propertyListHelper.Exists(m_propertyList, (e) -> e.GetId().equals(property.GetId())))
        {
            System.out.println("Property with same ID already in register");
            return false;
        }

        m_propertyList.add(new Property(property));
        return true;
    }

    @Override
    public Boolean TryUpdateProperty(Property property) 
    {
        var propertyListHelper = new ListHelper<Property>();
        if(!propertyListHelper.Exists(m_propertyList, (e) -> e.GetId().equals(property.GetId())))
        {
            System.out.println("Property with id "+property.GetId()+" is not registered. Fail to update");
            return false;
        }

        m_propertyList.set
        (
            propertyListHelper.GetIndexOf(m_propertyList, (e) -> e.GetId().equals(property.GetId())), 
            new Property(property)
        );
        return true;
    }

    @Override
    public Boolean TryRemoveProperty(String id) 
    {
        var propertyListHelper = new ListHelper<Property>();
        if(!propertyListHelper.Exists(m_propertyList, (e) -> e.GetId().equals(id)))
        {
            System.out.println("Property with id "+id+" is not registered. Fail to remove.");
            return false;
        }

        m_propertyList.remove(propertyListHelper.GetIndexOf(m_propertyList, (e) -> e.GetId().equals(id)));
        return true;
    }

    // Busca por referências (chaves estrangeiras) a um cpf. As tabelas que 
    // são varridas podem ser identificadas no corpo da função
    private int GetNumberOfReferences(String cpf)
    {
        var worksOnListHelper = new ListHelper<WorksOn>();
        var managerWarehouseListHelper = new ListHelper<ManageWarehouse>();
        var isWarehouseOwnerListHelper = new ListHelper<IsWarehouseOwner>();
        var buyBagListHelper = new ListHelper<BuyBag>();

        var inWorksOn = worksOnListHelper.FindAllThat(m_worksOnList, (e) -> e.GetCpf().equals(cpf));
        var inManageWarehouse = managerWarehouseListHelper.FindAllThat(m_manageWarehouseList, (e) -> e.GetManagerCpf().equals(cpf));
        var inIsWarehouseOwner = isWarehouseOwnerListHelper.FindAllThat(m_isWarehouseOwnerList, (e) -> e.GetOwnerCpf().equals(cpf));
        var inBuyBag = buyBagListHelper.FindAllThat(m_buyBagList, (e) -> e.GetCpf().equals(cpf));
        
        return inWorksOn.size() + inManageWarehouse.size() + inIsWarehouseOwner.size() + inBuyBag.size();
    }
}
