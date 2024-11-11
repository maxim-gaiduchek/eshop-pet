import {createBrowserRouter} from "react-router-dom";
import {IndexPage} from "../Pages/IndexPage";
import {ShopPage} from "../Pages/ShopPage";
import {LoginPage} from "../Pages/LoginPage";
import {ProductPage} from '../Pages/ProductPage';
import {MainLayout} from "../Components/Layouts/MainLayout";
import {RegistrationPage} from "../Pages/RegistrationPage";
import {CustomerPage} from "../Pages/CustomerPage";
import {SellersPage} from "../Pages/SellersPage";
import {SellerCreationPage} from "../Pages/SellerCreationPage";
import {CompaniesPage} from "../Pages/CompaniesPage";
import {CompanyCreationPage} from "../Pages/CompanyCreationPage";
import {CompanyPage} from "../Pages/CompanyPage";
import {ProductCreationPage} from "../Pages/ProductCreationPage";
import {SellerPage} from "../Pages/SellerPage";

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
        path: "/products/new",
        element: <ProductCreationPage/>
    },
    {
        path: "/users/:id",
        element: <CustomerPage/>
    },
    {
        path: "/sellers",
        element: <SellersPage/>
    },
    {
        path: "/sellers/new",
        element: <SellerCreationPage/>
    },
    {
        path: "/sellers/:id",
        element: <SellerPage/>
    },
    {
        path: "/companies",
        element: <CompaniesPage/>
    },
    {
        path: "/companies/new",
        element: <CompanyCreationPage/>
    },
    {
        path: "/companies/:id",
        element: <CompanyPage/>
    },
])
