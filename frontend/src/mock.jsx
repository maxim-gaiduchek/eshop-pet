export const mockCustomer = {
    id: 1,
    name: "Customer",
    surname: "Test",
    phone: "+420607777777",
    email: "test1@gmail.com",
    address: "Test str.",
    role: "ROLE_CUSTOMER",
};

export const mockSeller = {
    id: 1,
    name: "Seller",
    surname: "Test",
    phone: "+420607777777",
    email: "test1@gmail.com",
    address: "Test str.",
    companies: Array.from({length: 3}).map((_, j) => {
        return {
            id: 3 + j,
            name: "Company " + (3 + j),
        }
    }),
    role: "ROLE_SELLER",
};

export const mockSellers = Array.from({length: 100}).map((_, i) => {
    return {
        id: i,
        name: "Seller " + i,
        surname: i,
        phone: "+420607777777",
        email: `test${i}@gmail.com`,
        address: "Test str., " + i,
        companies: Array.from({length: 3}).map((_, j) => {
            return {
                id: 3 * i + j,
                name: "Company " + (3 * i + j),
            }
        }),
        role: "ROLE_SELLER",
    }
})

export const mockCompany = {
    id: 1,
    name: "Test company",
};

export const mockCompanies = Array.from({length: 300}).map((_, i) => {
    return {
        id: i,
        name: "Company " + i,
        seller: {
            id: Math.floor(i / 3),
            name: "Seller",
            surname: Math.floor(i / 3),
        },
    }
})

export const mockProduct = {
    id: 1,
    name: "Test",
    description: "Test description",
    cost: 300,
    count: 10,
    company: {
        id: 1,
        name: "Test company",
    },
}

export const mockProducts = Array.from({length: 100}).map((_, i) => {
    return {
        id: i,
        name: "Product " + i,
        description: "Test mocked product with id " + i,
        company: {
            id: Math.floor(i / 3),
            name: "Company " + Math.floor(i / 3),
        },
        cost: i * 10,
        count: 10,
    }
})
