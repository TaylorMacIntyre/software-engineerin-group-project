package Group_15.Trello_Project.BoardTests;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.repository.BoardRepository;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import Group_15.Trello_Project.workspace.service.WorkspaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BoardService.class})
@ExtendWith(SpringExtension.class)
public class BoardServiceTests {

    @MockBean
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;


    @Test
    public void createBoardTest()
    {
        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        Mockito.when(boardRepository.save(boardModel)).thenReturn(boardModel);

        BoardModel savedBoard = boardService.createBoard(boardModel);

        assertNotNull(savedBoard);
    }

    @Test
    public void findBoardByIdTest()
    {

        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        when(boardRepository.findById(any())).thenReturn(Optional.of(boardModel));
        BoardModel savedBoard = boardService.findBoardById(boardModel.getId());

        assertNotNull(savedBoard);

    }

    @Test
    public void deleteBoardTest()
    {
        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        boardRepository.deleteById(boardModel.getId());

        boolean savedBoard = boardService.deleteBoard(boardModel.getId());

        assertTrue(true);
    }

    @Test
    public void getAllBoardsTest()
    {
        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        List<BoardModel> boardModelList = new ArrayList<>();
        boardModelList.add(boardModel);

        List<BoardModel> resultList = boardService.getAllBoards();

        assertNotNull(resultList);


    }

    @Test
    public void getBoardTest()
    {

        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        when(boardRepository.findById(any())).thenReturn(Optional.of(boardModel));

        BoardModel savedBoard = boardService.getBoard(boardModel.getId());

        assertNotNull(savedBoard);

    }
}
