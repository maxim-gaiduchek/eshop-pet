import {createBrowserRouter} from "react-router-dom";
import {IndexPage} from "../Pages/IndexPage";
import {ShopPage} from "../Pages/ShopPage";
import {LoginPage} from "../Pages/LoginPage";
import {ProductPage} from '../Pages/ProductPage';
import {MainLayout} from "../Components/Layouts/MainLayout";
import {RegistrationPage} from "../Pages/RegistrationPage";
import {UserPage} from "../Pages/UserPage";
import {SellersPage} from "../Pages/SellersPage";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <IndexPage/>
    },
    {
        path: "/shop",
        element: <ShopPage/>
    },
    {
        path: "/login",
        element: <LoginPage/>
    },
    {
        path: "/register",
        element: <RegistrationPage/>
    },
    {
        path: "/products/:id",
        element:
            <MainLayout>
                <ProductPage/>
            </MainLayout>
    },
    {
        path: "/users/:id",
        element:
            <MainLayout>
                <UserPage/>
            </MainLayout>
    },
    {
        path: "/sellers",
        element: <SellersPage/>
    }
])
