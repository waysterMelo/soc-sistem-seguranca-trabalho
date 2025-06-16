import {Route, Routes} from 'react-router-dom';
import AppLayout from './layouts/AppLayout';
import DashboardPage from './features/dashboard/DashboardPage';
import EmpresaListPage from "./features/cadastros/empresa/EmpresaListPage.jsx";


function NotFound() {
    return <div className="p-6 text-center"><h2>404 - Página Não Encontrada</h2></div>;
}

function App() {
    return (
        <Routes>
            <Route path="/" element={<AppLayout />}>
                <Route index element={<DashboardPage />} />
                <Route path="cadastros/empresas" element={<EmpresaListPage />} />
            </Route>
        </Routes>
    );
}

export default App;