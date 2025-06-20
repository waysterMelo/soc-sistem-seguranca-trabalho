import {Route, Routes} from 'react-router-dom';
import AppLayout from './layouts/AppLayout';
import DashboardPage from './features/dashboard/DashboardPage';
import EmpresaListPage from "./features/cadastros/empresa/EmpresaListPage.jsx";
import CadastrarEmpresa from "./features/cadastros/empresa/CadastrarEmpresa.jsx";
import ListarUnidades from "./features/cadastros/empresa/ListarUnidades.jsx";
import CadastrarUnidade from "./features/cadastros/empresa/CadastrarUnidade.jsx";
import ListarSetores from "./features/cadastros/empresa/ListarSetores.jsx";
import CadastrarSetor from "./features/cadastros/empresa/CadastrarSetor.jsx";
import ListarFuncoes from "./features/cadastros/funcao/ListarFuncoes.jsx";
import CadastrarFuncao from "./features/cadastros/funcao/CadastrarFuncao.jsx";
import ListarFuncionarios from "./features/cadastros/funcionarios/ListarFuncionarios.jsx";
import CadastrarFuncionario from "./features/cadastros/funcionarios/CadastrarFuncionarios.jsx";
import ListarPrestadores from "./features/cadastros/Prestador/ListarPrestadorServicos.jsx";
import CadastrarPrestador from "./features/cadastros/Prestador/CadastrarPrestador.jsx";
import ListarEpiEpc from "./features/cadastros/Epi/ListarEpi.jsx";
import CadastrarEquipamento from "./features/cadastros/Epi/CadastrarEpi.jsx";
import ListarAparelhos from "./features/cadastros/aparelho/ListarAparelhos.jsx";
import ListarPGR from "./features/seguranca/ListarPGR.jsx";
import NovoPgr from "./features/seguranca/NovoPgr.jsx";
import ListarLTCAT from "./features/seguranca/ListarLTCAT.jsx";
import CadastrarLTCAT from "./features/seguranca/CadastrarLTCAT.jsx";
import ListarLTIP from "./features/seguranca/ListarLtip.jsx";
import CadastrarLTIP from "./features/seguranca/CadastrarLtip.jsx";
import CadastrarCAT from "./features/seguranca/CadastrarCAT.jsx";
import ListarCAT from "./features/seguranca/ListarCAT.jsx";
import ListarTreinamentos from "./features/seguranca/ListarTreinamentos.jsx";
import CadastrarTreinamento from "./features/seguranca/CadastrarTreinamento.jsx";
import MovimentacaoEPI from "./features/seguranca/MovimentacaoEPI.jsx";


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
                <Route path="cadastros/listar/setores" element={<ListarSetores />} />
                <Route path="cadastros/novo-setor" element={<CadastrarSetor />} />
                <Route path="cadastros/listar/funcao" element={<ListarFuncoes />} />
                <Route path="cadastros/funcao" element={<CadastrarFuncao />} />
                <Route path="cadastros/listar/funcionarios" element={<ListarFuncionarios />} />
                <Route path="cadastros/novo-funcionario" element={<CadastrarFuncionario />} />
                <Route path="cadastros/prestador-servico" element={<ListarPrestadores />} />
                <Route path="cadastros/novo-prestador" element={<CadastrarPrestador />} />
                <Route path="cadastros/epi-epc" element={<ListarEpiEpc />} />
                <Route path="cadastros/novo-epi" element={<CadastrarEquipamento />} />
                <Route path="cadastros/aparelhos" element={<ListarAparelhos />} />
                <Route path="seguranca/pgr" element={<ListarPGR />}/>
                <Route path="seguranca/novo-pgr" element={<NovoPgr />} />
                <Route path="seguranca/ltcat" element={<ListarLTCAT />} />
                <Route path="seguranca/novo-ltcat" element={<CadastrarLTCAT />} />
                <Route path="seguranca/ltip" element={<ListarLTIP />} />
                <Route path="seguranca/novo-ltip" element={<CadastrarLTIP />} />
                <Route path="seguranca/cat" element={<ListarCAT />} />
                <Route path="seguranca/novo-cat" element={<CadastrarCAT />} />
                <Route path="seguranca/treinamentos" element={<ListarTreinamentos />} />
                <Route path="seguranca/novo-treinamento" element={<CadastrarTreinamento />} />
                <Route path="seguranca/epi" element={<MovimentacaoEPI />} />
                <Route path="seguranca/ordem-servico" element={<MovimentacaoEPI />} />
            </Route>
        </Routes>
    );
}

export default App;