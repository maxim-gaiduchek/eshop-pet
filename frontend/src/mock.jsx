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
        })
    }
})

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

export const mockCompany = {
    id: 1,
    name: "Test company",
};

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
