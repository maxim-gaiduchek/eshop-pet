import {createBrowserRouter} from "react-router-dom";
import {IndexPage} from "../Pages/IndexPage";
import {ShopPage} from "../Pages/ShopPage";
import {LoginPage} from "../Pages/Login/LoginPage";
import {ProductPage} from '../Pages/Product/ProductPage';
import {MainLayout} from "../Components/Layouts/MainLayout";
import {RegistrationPage} from "../Pages/Login/RegistrationPage";
import {CustomerPage} from "../Pages/Customer/CustomerPage";
import {SellersPage} from "../Pages/Seller/SellersPage";
import {SellerCreationPage} from "../Pages/Seller/SellerCreationPage";
import {CompaniesPage} from "../Pages/Company/CompaniesPage";
import {CompanyCreationPage} from "../Pages/Company/CompanyCreationPage";
import {CompanyPage} from "../Pages/Company/CompanyPage";
import {ProductCreationPage} from "../Pages/Product/ProductCreationPage";
import {SellerPage} from "../Pages/Seller/SellerPage";
import {FilterCategoriesPage} from "../Pages/Settings/Filter/FilterCategoriesPage";
import {FilterCategoryPage} from "../Pages/Settings/Filter/FilterCategoryPage";
import {FilterCategoryCreationPage} from "../Pages/Settings/Filter/FilterCategoryCreationPage";
import {FilterCreationPage} from "../Pages/Settings/Filter/FilterCreationPage";

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
    {
        path: "/settings",
        element: <FilterCategoriesPage/>
    },
    {
        path: "/settings/filter-categories/new",
        element: <FilterCategoryCreationPage/>
    },
    {
        path: "/settings/filter-categories/:id",
        element: <FilterCategoryPage/>
    },
    {
        path: "/settings/filter-categories/:filterCategoryId/filters/new",
        element: <FilterCreationPage/>
    },
])
