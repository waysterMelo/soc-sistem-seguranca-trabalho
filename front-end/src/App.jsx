import {Route, Routes} from 'react-router-dom';
import AppLayout from './layouts/AppLayout';
import DashboardPage from './features/dashboard/DashboardPage';
import EmpresaListPage from "./features/cadastros/empresa/EmpresaListPage.jsx";
import CadastrarEmpresa from "./features/cadastros/empresa/CadastrarEmpresa.jsx";
import ListarUnidades from "./features/cadastros/empresa/ListarUnidades.jsx";
import CadastrarUnidade from "./features/cadastros/empresa/CadastrarUnidade.jsx";


function NotFound() {
    return <div className="p-6 text-center"><h2>404 - Página Não Encontrada</h2></div>;
}

function App() {
    return (
        <Routes>
            <Route path="/" element={<AppLayout />}>
                <Route index element={<DashboardPage />} />
                <Route path="cadastros/listar/empresas" element={<EmpresaListPage />} />
                <Route path="cadastros/nova-empresa" element={<CadastrarEmpresa />} />
                <Route path="cadastros/listar/unidades" element={<ListarUnidades />} />
                <Route path="cadastros/nova-unidade" element={<CadastrarUnidade />} />
            </Route>
        </Routes>
    );
}

export default App;