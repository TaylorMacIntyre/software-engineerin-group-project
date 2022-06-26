package Group_15.Trello_Project.board.service;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.repository.BoardRepository;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

//    //CONNECTION TO TAYLOR'S BACKEND
//    @Autowired
//    UserService userService;
//
//    public BoardModel createBoard(Long user_id, BoardModel boardModel) {
//
//        userService.addWorkspaceToUser(user_id, boardModel);
//        return boardRepository.save(boardModel);
//    }

    public BoardModel findBoardById(Long board_id)
    {
        BoardModel boardModel = null;

        Optional<BoardModel> optionalBoardModel = boardRepository.findById(board_id);

        if(optionalBoardModel.isPresent())
        {
            boardModel = optionalBoardModel.get();
        }

        return boardModel;
    }

    public void deleteBoard(@PathVariable Long board_id)
    {
        boardRepository.deleteById(board_id);
    }

    public List<BoardModel> getAllBoards()
    {
        return boardRepository.findAll();
    }


    public BoardModel getBoard(@PathVariable Long board_id)
    {
        BoardModel boardModel = null;
        Optional<BoardModel> board = null;

        Optional<BoardModel> optionalBoardModel = boardRepository.findById(board_id);

        if(optionalBoardModel.isPresent()) {

            boardModel = optionalBoardModel.get();
        }


        return boardModel;

    }

}
