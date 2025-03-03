// CartContext.jsx
import React, { createContext, useReducer, useContext } from "react";

const CartContext = createContext();
const cartReducer = (state, action) => {
  switch (action.type) {
    case "ADD_TO_CART":
      return state.some((item) => item.id === action.payload.id)
        ? state
        : [...state, { ...action.payload, quantity: 1 }];
    case "INCREMENT":
      return state.map((item) =>
        item.id === action.payload
          ? { ...item, quantity: item.quantity + 1 }
          : item
      );
    case "DECREMENT":
      return state.map((item) =>
        item.id === action.payload && item.quantity > 1
          ? { ...item, quantity: item.quantity - 1 }
          : item
      );
    case "REMOVE":
      return state.filter((item) => item.id !== action.payload);
    default:
      return state;
  }
};

export const CartProvider = ({ children }) => {
  const [cart, dispatch] = useReducer(cartReducer, []);
  return (
    <CartContext.Provider value={{ cart, dispatch }}>
      {children}
    </CartContext.Provider>
  );
};
export const useCart = () => useContext(CartContext);
