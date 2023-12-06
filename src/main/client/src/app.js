import * as React from 'react';

import ProductsPage from "./components/ProductsPage";
import Login from "./components/Login"
import ProfilePage from "./components/ProfilePage";
import {BrowserRouter, HashRouter, Route, Routes} from "react-router-dom";
import SignUp from "./components/SignUp";
import {
    createBrowserRouter,
} from "react-router-dom";

import UsersAdminPage from "./components/usersAdminPage";
import AppExperiment from "./appExperiment";
import Dashboard from "./components/dashboard";
import ProductsAdminPage from "./components/productsAdminPage";



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
                    <Route path="/" element={<ProductsPage/>}/>
                    <Route path="/signup" element={<SignUp/>}/>
                    <Route path="/products" element={<ProductsPage/>}/>
                    <Route path="/admin" element={<AppExperiment/>}> //no element attribute here
                        <Route path="" element={<Dashboard />}></Route>
                        <Route path="products" element={<ProductsAdminPage/>}></Route>
                        <Route path="users" element={<UsersAdminPage/>}></Route>
                    </Route>
                </Routes>
            </HashRouter>
        </React.StrictMode>
    )
}

export default App