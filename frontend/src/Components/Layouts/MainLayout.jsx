export function MainLayout({penisSize, setMaxim}) {
    return (
        <button onClick={() => {
            setMaxim("pidor")
        }}>set maxim</button>
    )
}