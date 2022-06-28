package Group_15.Trello_Project.board.controller;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping(path = "/saveBoard", consumes = "application/json", produces = "application/json")
    public BoardModel createBoard(@RequestBody BoardModel boardModel) {
        return boardService.createBoard(boardModel);
    }


//    //CONNECTION TO TAYLOR'S BACKEND
//    @PostMapping(path = "/saveBoard", consumes = "application/json", produces = "application/json")
//    public BoardModel createBoard(@PathVariable Integer user_id, @RequestBody BoardModel boardModel) {
//        return boardService.createBoard(user_id, boardModel);
//    }

    @DeleteMapping("/deleteBoard/{board_id}")
    public void deleteBoard(@PathVariable Integer board_id)
    {
        boardService.deleteBoard(board_id);
    }

    @GetMapping("/getAllBoards")
    public List<BoardModel> getAllBoards()
    {
        return boardService.getAllBoards();
    }

    @GetMapping("/getBoard/{board_id}")
    public BoardModel getBoard(@PathVariable Integer board_id)
    {
        return boardService.getBoard(board_id);
    }


}
