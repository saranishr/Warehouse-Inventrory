
import {getProducts} from '../utils/api';
const Dasboard=()=>{
    const[product,setproducts]=useState([]);
    useEffect(()=>{
     const fetchData=async()=>{
        const data=await getProducts();
        setProducts(data);
        
     };
     fetchData();
    },[]);
    const totalInventoryvalue= products.reduce((sum,p) => sum+p.price*p.price*p.quantity,0);
    const lowstockitems=products.filter(p=>p.quantity<10);
    return(
        <div>
            <h2>Dashboard</h2>
             <div className="summary-cards">
                <p>Total Products :{products.length}</p>
                <p>Total Inventory Value: ₹{totalInventoryvalue.toFixed(2)}</p>
                <p>Low Stock Items:{lowstockitems.length}</p>
                </div>
                <h3>Low Stock List</h3>
                <ul>
                    {lowstockitems.map(p=><li key={p.id}>{p.name}-Qty:{p.quantity}</li>)}
                    </ul>
                    </div>
    );

};
