import * as React from 'react';

import ProductsPage from "./components/ProductsPage";
import Login from "./components/Login"
import ProfilePage from "./components/ProfilePage";
import {HashRouter, Route, Routes} from "react-router-dom";

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
                    <Route path="/login" element={<Login/>}/> {/* 👈 Renders at /#/app/ */}
                    <Route path="/profile" element={<ProfilePage/>}/> {/* 👈 Renders at /#/app/ */}
                    <Route path="/products" element={<ProductsPage/>}/> {/* 👈 Renders at /#/app/ */}
                    <Route path="/" element={<div>HLO</div>}/> {/* 👈 Renders at /#/app/ */}
                </Routes>
            </HashRouter>
        </React.StrictMode>
    )
}

export default App