import {getProducts,deleteProduct} from '../utils/api';
const ProductList =({onEdit})=>{
    const [products,setproducts]=useState([]);
    const fetchProducts=async ()=>{
        const data=await getProducts();
        setproducts(data);
    };
    useEffect(()=>{
        fetchProducts();

    },[]);
    const handleDelete =async (id)=>{
        await deleteProduct(id);
        fetchProducts();
    };
    return (
        <div>
            <h2>
                Product List
            </h2>
            <button onClick={()=> onEdit(null)}>Add Button</button>
            <table>
                <thead>
                    <tr>
                        <th>Name</th><th>Category</th><th>Quantity</th><th>Price</th><th>Location</th><th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            {products.map(p=>(
                                <tr key={p.id}>
                                    <td>{p.name}</td><td>{p.category}</td><td>{p.quantity}</td><td>{p.price}</td><td>{p.location}</td>
                                    <td>
                                        <button onClick={()=>onEdit(p)}>Edit</button>
                                        <button onClick={()=> handleDelete(p.id)}>Delete</button>
                                    </td>
                                    </tr>
                            ))}
                            </tbody>
                            </table>
                            </div>
    
    
    );
};
