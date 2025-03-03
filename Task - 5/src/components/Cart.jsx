// Cart.jsx
import { useCart } from "../context/CartContext";
const Cart = () => {
  const { cart, dispatch } = useCart();
  return (
    <div className="p-4">
      {cart.map((item) => (
        <div
          key={item.id}
          className="border p-4 m-2 bg-white shadow-lg rounded-lg flex items-center"
        >
          <img
            src={item.image}
            alt={item.name}
            className="w-16 h-16 object-cover rounded-lg"
          />
          <div className="ml-4 flex-1">
            <h2 className="text-lg font-bold">{item.name}</h2>
            <p>${item.price}</p>
            <div className="flex items-center mt-2">
              <button
                className="bg-red-500 text-white px-2 py-1"
                onClick={() =>
                  dispatch({ type: "DECREMENT", payload: item.id })
                }
              >
                -
              </button>
              <span className="px-4">{item.quantity}</span>
              <button
                className="bg-green-500 text-white px-2 py-1"
                onClick={() =>
                  dispatch({ type: "INCREMENT", payload: item.id })
                }
              >
                +
              </button>
              <button
                className="bg-red-600 text-white px-2 py-1 ml-2"
                onClick={() => dispatch({ type: "REMOVE", payload: item.id })}
              >
                Remove
              </button>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};
export default Cart;
