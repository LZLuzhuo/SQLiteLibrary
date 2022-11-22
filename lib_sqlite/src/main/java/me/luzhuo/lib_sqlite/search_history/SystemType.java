/* Copyright 2022 Luzhuo. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.luzhuo.lib_sqlite.search_history;

/**
 * Description: 预留的历史记录类型
 * @Author: Luzhuo
 * @Creation Date: 2022/2/22 19:46
 * @Copyright: Copyright 2022 Luzhuo. All rights reserved.
 **/
public interface SystemType {
    /**
     * 权限库 - 永久被拒绝的权限记录
     */
    public static int Type_Permission_ForeverDenied_History = 0x901;
}
