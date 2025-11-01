package mybatisx-generate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mybatisx-generate.entity.Students;
import mybatisx-generate.service.StudentsService;
import mybatisx-generate.mapper.StudentsMapper;
import org.springframework.stereotype.Service;

/**
* @author 10952
* @description 针对表【students】的数据库操作Service实现
* @createDate 2025-02-24 12:48:38
*/
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students>
    implements StudentsService{

}




