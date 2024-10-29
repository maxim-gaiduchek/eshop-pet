import {createBrowserRouter} from "react-router-dom";
import {IndexPage} from "../Pages/IndexPage";
import {ShopPage} from "../Pages/ShopPage";
import {LoginPage} from "../Pages/LoginPage";
import {ProductPage} from '../Pages/ProductPage';
import {MainLayout} from "../Components/Layouts/MainLayout";

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
        path: "/product/:id",
        element: <MainLayout>
            <ProductPage/>
        </MainLayout>
    }
])
