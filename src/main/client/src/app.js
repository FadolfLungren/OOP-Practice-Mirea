import * as React from 'react';

import ProductsPage from "./components/ProductsPage";
import Login from "./components/Login"
import ProfilePage from "./components/ProfilePage";
import {HashRouter, Route, Routes} from "react-router-dom";
import SignUp from "./components/SignUp";
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";



const router = createBrowserRouter([
    {
        path: "/",
        element: <div>Hell3o world!</div>,
    },
    {
        path: "/#/login",
        element: <Login/>,
    },
    {
        path: "/#/profile",
        element: <ProfilePage/>,
    },
    {
        path: "/#/products",
        element: <ProductsPage/>,
    }
]);



const App = () => {
    return (
        <React.StrictMode>
            <HashRouter>
                <Routes>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/profile" element={<ProfilePage/>}/>
                    <Route path="/products" element={<ProductsPage/>}/>
                    <Route path="/" element={<div>HLO</div>}/>
                    <Route path="/signup" element={<SignUp/>}/>
                </Routes>
            </HashRouter>
        </React.StrictMode>
    )
}

export default App